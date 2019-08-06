package com.xinhoo.database2javabean.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @ClassName: FileUtil
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/21 18:00
 */
public class FileUtil {
    static Logger logger = LoggerFactory.getLogger(FileUtil.class.getSimpleName());

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static void deleteFile(File file){
        if (file.isFile()){//判断是否为文件，是，则删除
            boolean result = file.delete();
            logger.info(String.valueOf(result)+" "+String.valueOf(file.getAbsoluteFile()));
        }else{//不为文件，则为文件夹
            String[] childFilePath = file.list();//获取文件夹下所有文件相对路径
            for (String path:childFilePath){
                File childFile= new File(file.getAbsoluteFile()+File.separator+path);
                deleteFile(childFile);//递归，对每个都进行判断
            }
            boolean result = file.delete();
            logger.info(String.valueOf(result)+" "+String.valueOf(file.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) {
        //删除创建的文件和文件夹
        //FileUtil.deleteFile(new File("G:\\陈超超-2019-05-13\\database2javabean\\.database2javabeanfiles\\eed81cce-82e6-4a13-9dee-6545a285477e.zip"));
        FileUtil.deleteFile(new File("G:\\陈超超-2019-05-13\\database2javabean\\.database2javabeanfiles\\eed81cce-82e6-4a13-9dee-6545a285477e"));
    }
}
