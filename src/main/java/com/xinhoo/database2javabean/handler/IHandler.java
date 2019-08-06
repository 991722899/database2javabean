package com.xinhoo.database2javabean.handler;

import com.xinhoo.database2javabean.model.DBConfig;
import com.xinhoo.database2javabean.model.JavaBeanConfig;
import com.xinhoo.database2javabean.model.MetaData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: IHandler
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 16:36
 */
public interface IHandler {

    public boolean testConnection(DBConfig dbConfig) throws Exception;
    public List<String> getTableNames(DBConfig dbConfig) throws Exception;
    public List<MetaData> getMetaDatas(DBConfig dbConfig, JavaBeanConfig javaBeanConfig) throws Exception;
    public Connection getConnection(DBConfig dbConfig) throws Exception;
}
