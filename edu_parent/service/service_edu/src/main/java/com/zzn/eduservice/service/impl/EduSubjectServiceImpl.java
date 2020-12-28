package com.zzn.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zzn.eduservice.entity.EduSubject;
import com.zzn.eduservice.entity.excel.SubjectData;
import com.zzn.eduservice.listener.SubjectExcelListener;
import com.zzn.eduservice.mapper.EduSubjectMapper;
import com.zzn.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzn.servicebase.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zhangzhaonian
 * @since 2020-12-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    //poi读取excel内容
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //1、获取文件输入流
            InputStream in = file.getInputStream();
            //2、读取文件，存入数据库
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "添加课程失败");
        }
    }
}
