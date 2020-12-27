package com.zzn.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Program : edu_parent
 * @ClassName : EduApplication
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-11-29 21:33
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zzn")
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
