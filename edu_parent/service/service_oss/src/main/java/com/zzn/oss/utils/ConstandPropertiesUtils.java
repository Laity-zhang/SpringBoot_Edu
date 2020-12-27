package com.zzn.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Program : edu_parent
 * @ClassName : ConstandPropertiesUtils
 * @Description : 读取配置文件中的内容
 * @Author : zhangzhaonian
 * @Date: 2020-12-23 18:21
 */

//当项目启动起来，spring接口，spring加载接口，执行接口的一个方法
@Component
public class ConstandPropertiesUtils implements InitializingBean {

    //读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //定义公开静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
