package com.xinhoo.database2javabean.model;

/**
 * @ClassName: MetaColumn
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/21 10:51
 */
public class MetaColumn {
    private String column_name;
    private String column_comment;
    private String column_dataType;

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_comment() {
        return column_comment;
    }

    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

    public String getColumn_dataType() {
        return column_dataType;
    }

    public void setColumn_dataType(String column_dataType) {
        this.column_dataType = column_dataType;
    }
}
