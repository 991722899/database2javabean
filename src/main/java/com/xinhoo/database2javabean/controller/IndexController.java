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

            Map<String,Object> map = new HashMap<>();
            map.put("conf",javaBeanConfig);
            for(MetaData m : metaDataList){
                for(String str : javaBeanConfig.getPackageName()){
                    String[]s = str.split("-");
                    map.put("data",m);
                    map.put("packageName",s[1]);

                    File tempFile = new File(dir.concat(File.separator).concat(s[1].replace(".",File.separator)).concat(File.separator).concat(m.getTable_name().toLowerCase()));
                    if(!tempFile.exists()){
                        tempFile.mkdirs();
                    }
                    String  fileName = "";
                    if(s[0].equals("mapper")){
                        fileName = tempFile.getPath().concat(File.separator).concat(StringUtils.uncapitalize(m.getTable_name())).concat(".xml");
                    }else{
                        fileName = tempFile.getPath().concat(File.separator).concat(StringUtils.capitalize(m.getTable_name())).concat(StringUtils.capitalize(s[0])).concat(".java");
                    }

                    createFile(map,fileName,s[0]+".ftl");
                }
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
