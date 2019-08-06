package com.xinhoo.database2javabean.controller;

import com.xinhoo.database2javabean.context.ApplicationContextHolder;
import com.xinhoo.database2javabean.handler.IHandler;
import com.xinhoo.database2javabean.model.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: BaseController
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/21 9:49
 */
public class BaseController {
    protected  Logger logger = LoggerFactory.getLogger(getClass());

    private @Autowired
    ApplicationContextHolder applicationContextHolder;

    public IHandler getHandler(DBConfig dbConfig) throws Exception {
        IHandler iHandler =  applicationContextHolder.getBean(dbConfig.getDb_type());
        if(iHandler==null){
            throw new Exception("不支持的数据库类型");
        }
        return iHandler;
    }
}
