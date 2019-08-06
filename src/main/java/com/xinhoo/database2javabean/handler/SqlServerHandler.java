package com.xinhoo.database2javabean.handler;

import com.xinhoo.database2javabean.model.DBConfig;
import com.xinhoo.database2javabean.model.JavaBeanConfig;
import com.xinhoo.database2javabean.model.MetaColumn;
import com.xinhoo.database2javabean.model.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: SqlServerHandler
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 16:46
 */
@Component
public class SqlServerHandler extends AbsHandler {

    @Override
    public boolean testConnection(DBConfig dbConfig) throws Exception {
       return super.testConnection(dbConfig);
    }

    @Override
    public List<String> getTableNames(DBConfig dbConfig) throws Exception {
        return super.getTableNames(dbConfig);
    }

    @Override
    public List<MetaData> getMetaDatas(DBConfig dbConfig, JavaBeanConfig javaBeanConfig) throws Exception{
        return super.getMetaDatas(dbConfig,javaBeanConfig);
    }

    @Override
    public Connection getConnection(DBConfig dbConfig) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(String.format("jdbc:sqlserver://%s:%d;databaseName=%s",dbConfig.getIp(),dbConfig.getPort(),dbConfig.getName()), dbConfig.getUser_name(), dbConfig.getUser_pwd());
    }
}
