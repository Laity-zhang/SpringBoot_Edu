package com.zzn.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zzn.oss.service.OssService;
import com.zzn.oss.utils.ConstandPropertiesUtils;
import lombok.val;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Program : edu_parent
 * @ClassName : OssServiceImpl
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-23 18:44
 */
@Service
public class OssServiceImpl implements OssService {

    //上传头像到oss中
    @Override
    public String uploadFileAvatar(MultipartFile file){

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstandPropertiesUtils.END_POINT;
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String filename = file.getOriginalFilename();

            //在文件名里添加唯一值uuid,防止上传的文件因文件名重复覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //拼接文件名
            filename = uuid + filename;

            //文件按照日期进行分类
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //第二次拼接文件名
            filename = datePath +"/"+ filename;

            //调用OSS中的方法实现上传
            //第一个参数 bucket名称
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件的输入流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient
            ossClient.shutdown();

            //把上传到oss的文件路径拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+filename;

            //把上传之后的路径返回
            return url;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
