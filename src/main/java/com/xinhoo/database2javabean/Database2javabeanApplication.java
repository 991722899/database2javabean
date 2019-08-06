package com.xinhoo.database2javabean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sun.rmi.runtime.Log;

import java.io.File;

@SpringBootApplication
public class Database2javabeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(Database2javabeanApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread
                (new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(System.getProperty("user.dir").concat(File.separator).concat(".database2javabeanfiles"));
                        if(file.exists()){
                            file.delete();
                        }
                    }
                }));
    }

}
