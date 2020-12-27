package com.zzn.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Program : edu_parent
 * @ClassName : OssApplication
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-23 17:54
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//默认不加载数据库配置
@ComponentScan(basePackages = {"com.zzn"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
