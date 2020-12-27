package com.zzn.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzn.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzn.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhangzhaonian
 * @since 2020-11-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //按条件组合分页查询
    void  pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery);
}
