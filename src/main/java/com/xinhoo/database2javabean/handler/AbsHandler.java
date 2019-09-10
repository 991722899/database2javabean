package com.xinhoo.database2javabean.handler;

import com.sun.xml.internal.ws.api.model.MEP;
import com.xinhoo.database2javabean.model.DBConfig;
import com.xinhoo.database2javabean.model.JavaBeanConfig;
import com.xinhoo.database2javabean.model.MetaColumn;
import com.xinhoo.database2javabean.model.MetaData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: AbsHandler
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/21 11:18
 */
public abstract class AbsHandler implements IHandler {
    protected  Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean testConnection(DBConfig dbConfig) throws Exception {
        boolean result = false;
        Connection conn = null;
        try {
            conn = getConnection(dbConfig);
            result = conn!=null;
        }catch (Exception e){
            throw e;
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error("关闭数据库连接对象异常："+e.getLocalizedMessage());
                }
            }
        }
        return result;
    }

    @Override
    public List<String> getTableNames(DBConfig dbConfig) throws  Exception {
        List<String> tableNames = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection(dbConfig);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
            while (resultSet.next()){
                String tableName = resultSet.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
        }catch (Exception e){
            throw e;
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error("关闭数据库连接对象异常："+e.getLocalizedMessage());
                }
            }
        }
        return tableNames;
    }

    @Override
    public List<MetaData> getMetaDatas(DBConfig dbConfig, JavaBeanConfig javaBeanConfig) throws Exception  {
        Connection conn = null;
        List<MetaData> metaDataList = new ArrayList<>();
        try {
            conn = getConnection(dbConfig);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
            List<String> tableNames = Arrays.asList(dbConfig.getTableNames());
            while (resultSet.next()){
                MetaData metaData = new MetaData();
                String tableName = resultSet.getString("TABLE_NAME");
                if(tableNames.contains(tableName)){
                    if(StringUtils.isNotBlank(javaBeanConfig.getReplaceDBPre())){
                        metaData.setTable_name(tableName.replace(javaBeanConfig.getReplaceDBPre(),""));
                        if(metaData.getTable_name().indexOf("_")!=-1){
                            String[]str = metaData.getTable_name().split("_");
                            String tempName = "";
                            for(int i=0;i<str.length;i++){
                                if(i!=0){
                                    tempName+=StringUtils.capitalize(str[i]);
                                }else{
                                    tempName+=StringUtils.capitalize(str[i]);
                                }
                            }
                            metaData.setTable_name(tempName);
                        }
                    }else{
                        metaData.setTable_name(tableName);
                    }

                    ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                    List<MetaColumn> list = new ArrayList<>();
                    while(rs.next()){
                        MetaColumn metaColumn = new MetaColumn();
                        metaColumn.setColumn_comment(rs.getString("REMARKS"));
                        metaColumn.setColumn_dataType(dbType2JavaType(rs.getString("TYPE_NAME")));
                        metaColumn.setColumn_name(rs.getString("COLUMN_NAME"));
                        list.add(metaColumn);
                    }
                    metaData.setList(list);
                    metaDataList.add(metaData);
                }
            }
        }catch (Exception e){
           throw e;
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error("关闭数据库连接对象异常："+e.getLocalizedMessage());
                }
            }
        }
        return metaDataList;
    }

    public String dbType2JavaType(String typeName){
        typeName = typeName.toUpperCase();
        if(typeName.indexOf("CHAR")!=-1){
            return "String";
        }else if(typeName.equals("NUMBER") || typeName.equals("DECIMAL")){
            return "Double";
        }else if(typeName.equals("INT") || typeName.equals("SMALLINT") || typeName.equals("INTEGER") || typeName.equals("BIGINT")){
            return "Integer";
        }else if(typeName.equals("DATETIME") || typeName.equals("TIMESTAMP") || typeName.equals("DATE")){
            return "Date";
        }else{
            return "String";
        }
    }
}
