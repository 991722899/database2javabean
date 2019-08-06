package com.xinhoo.database2javabean.controller;

import com.xinhoo.database2javabean.context.ApplicationContextHolder;
import com.xinhoo.database2javabean.handler.IHandler;
import com.xinhoo.database2javabean.model.DBConfig;
import com.xinhoo.database2javabean.model.JavaBeanConfig;
import com.xinhoo.database2javabean.model.MetaData;
import com.xinhoo.database2javabean.model.ResultModel;
import com.xinhoo.database2javabean.util.FileUtil;
import com.xinhoo.database2javabean.util.ZipUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: IndexController
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 15:52
 */
@RequestMapping
@Controller
public class IndexController extends BaseController {
    private  final String rootDirName = ".database2javabeanfiles";
    private @Autowired Configuration configuration;

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public ResultModel testConnection(DBConfig dbConfig) throws Exception {
        return ResultModel.success(getHandler(dbConfig).testConnection(dbConfig));
    }

    @RequestMapping("/getTableNames")
    @ResponseBody
    public ResultModel getTableNames(DBConfig dbConfig) throws Exception {
        return ResultModel.success(getHandler(dbConfig).getTableNames(dbConfig));
    }

    @RequestMapping("/export")
    public void export(DBConfig dbConfig, JavaBeanConfig javaBeanConfig, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(javaBeanConfig.getPackageName()==null || javaBeanConfig.getPackageName().length==0){
            throw new Exception("请选择需要导出的模块");
        }
        List<MetaData> metaDataList = getHandler(dbConfig).getMetaDatas(dbConfig,javaBeanConfig);
        if(metaDataList!=null && metaDataList.size()>0){
            //创建主目录和当前请求任务的文件目录
            String rootPath = System.getProperty("user.dir").concat(File.separator).concat(rootDirName);
            String itemName = UUID.randomUUID().toString();
            String dir = rootPath.concat(File.separator).concat(itemName);
            String zipFilePath = rootPath.concat(File.separator).concat(itemName).concat(".zip");
            File file = new File(dir);
            if(!file.exists()){
                file.mkdirs();
            }
            //默认包名
            Map<String,String> defaultPackageName = new HashMap<>();
            defaultPackageName.put("vo","com".concat(File.separator).concat("xinhoo").concat(File.separator).concat("vo"));
            defaultPackageName.put("dao","com".concat(File.separator).concat("xinhoo").concat(File.separator).concat("dao"));

            //创建包路径

            File fileDao = new File(file.getPath().concat(File.separator).concat(defaultPackageName.get("dao")));
            File fileBean = new File(file.getPath().concat(File.separator).concat(defaultPackageName.get("vo")));
            fileDao.mkdirs();
            fileBean.mkdirs();

            Map<String,Object> map = new HashMap<>();
            map.put("conf",javaBeanConfig);
            for(MetaData m : metaDataList){
                map.put("data",m);
                map.put("packageName",defaultPackageName.get("vo").replace(File.separator,"."));
                String fileName = fileBean.getPath().concat(File.separator).concat(StringUtils.capitalize(m.getTable_name())).concat(".java");
                createFile(map,fileName,"vo.ftl");

                map.put("packageName",defaultPackageName.get("dao").replace(File.separator,"."));
                fileName = fileDao.getPath().concat(File.separator).concat(StringUtils.capitalize(m.getTable_name())).concat("DAO").concat(".java");
                createFile(map,fileName,"dao.ftl");
            }

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(itemName.concat(".zip"), "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");

            OutputStream ops = null;
            FileInputStream fis =null;
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            try {
                ZipUtils.compress(dir.concat(File.separator).concat("com"),zipFilePath);
                ops = response.getOutputStream();
                fis = new FileInputStream(rootPath.concat(File.separator).concat(itemName).concat(".zip"));
                while((bytesRead = fis.read(buffer, 0, 8192)) != -1){
                    ops.write(buffer, 0, bytesRead);
                }
                ops.flush();
            } catch (IOException e) {
                throw e;
            } finally {
                try {
                    if(fis != null){
                        fis.close();
                    }
                    if(ops != null){
                        ops.close();
                    }
                    //删除创建的文件和文件夹
                    FileUtil.deleteFile(new File(zipFilePath));
                    FileUtil.deleteFile(new File(dir));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void createFile(Map<String,Object> map,String fileName,String templateName) throws IOException, TemplateException {
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        try {
            configuration.getTemplate(templateName).process(map,osw);
        }catch (Exception e){
            throw e;
        }finally {
            if(fos!=null){
                fos.close();
            }
            if(osw!=null){
                osw.close();
            }
        }
    }
}
