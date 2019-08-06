package com.xinhoo.database2javabean.model;

import javax.xml.transform.Result;

/**
 * @ClassName: ResultModel
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/22 10:40
 */
public class ResultModel {
    private String code;
    private Object data;
    private String msg;

    public ResultModel(String code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResultModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultModel success(Object object){
        return new ResultModel("0",object,null);
    }
    public static ResultModel error(String msg){
        return new ResultModel("1",msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
