package com.xinhoo.database2javabean.util;

import org.apache.commons.lang3.SystemUtils;

/**
 * @ClassName: ApacheLang3Test
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/6/10 13:54
 */
public class ApacheLang3Test {
    public static void main(String[] args) {
        System.out.println("getJavaIoTmpDir："+SystemUtils.getJavaIoTmpDir());
        System.out.println("getHostName："+SystemUtils.getHostName());
        System.out.println("getUserDir："+SystemUtils.getUserDir());
        System.out.println("getUserHome："+SystemUtils.getUserHome());

    }
}
