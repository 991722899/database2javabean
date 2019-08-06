package com.xinhoo.database2javabean.model;

import java.util.List;

/**
 * @ClassName: MetaData
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/21 10:45
 */
public class MetaData {
    private String table_name;
    private List<MetaColumn> list;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public List<MetaColumn> getList() {
        return list;
    }

    public void setList(List<MetaColumn> list) {
        this.list = list;
    }
}
