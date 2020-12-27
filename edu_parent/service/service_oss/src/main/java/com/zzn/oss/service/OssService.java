package com.zzn.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Program : edu_parent
 * @InterfaceName : OssService
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-23 18:44
 */
public interface OssService {

    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
