package com.xinhoo.database2javabean.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationContextHolder
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 17:05
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext==null){
            this.applicationContext = applicationContext;
        }

    }

    public  ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public  <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public  <T> T getBean(String name){
        return (T)applicationContext.getBean(name);
    }

    public  <T> T getBean(String name, Class<T> clazz){
        return getBean(name, clazz);
    }

}
