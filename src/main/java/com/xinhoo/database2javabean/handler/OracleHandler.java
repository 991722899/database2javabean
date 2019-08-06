package com.xinhoo.database2javabean.handler;

import com.xinhoo.database2javabean.model.DBConfig;
import com.xinhoo.database2javabean.model.JavaBeanConfig;
import com.xinhoo.database2javabean.model.MetaData;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: OracleHandler
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 16:46
 */
@Component
public class OracleHandler extends AbsHandler {

    @Override
    public boolean testConnection(DBConfig dbConfig) throws Exception {
       return super.testConnection(dbConfig);
    }

    @Override
    public List<String> getTableNames(DBConfig dbConfig) throws Exception {
        return super.getTableNames(dbConfig);
    }

    @Override
    public List<MetaData> getMetaDatas(DBConfig dbConfig, JavaBeanConfig javaBeanConfig) throws Exception {
        return super.getMetaDatas(dbConfig,javaBeanConfig);
    }

    @Override
    public Connection getConnection(DBConfig dbConfig) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(String.format("jdbc:oracle:thin:@%s:%d:%s",dbConfig.getIp(),dbConfig.getPort(),dbConfig.getName()), dbConfig.getUser_name(), dbConfig.getUser_pwd());
    }
}
