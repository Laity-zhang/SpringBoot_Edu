package com.zzn.eduservice.service;

import com.zzn.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzn.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zhangzhaonian
 * @since 2020-12-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getSubject();
}
