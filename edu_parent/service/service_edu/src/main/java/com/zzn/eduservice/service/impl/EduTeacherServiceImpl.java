package com.zzn.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzn.eduservice.entity.EduTeacher;
import com.zzn.eduservice.entity.vo.TeacherQuery;
import com.zzn.eduservice.mapper.EduTeacherMapper;
import com.zzn.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zhangzhaonian
 * @since 2020-11-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //按条件组合分页查询
    @Override
    public void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery) {

        //构造条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        //查询后，根据创建时间排序
        queryWrapper.orderByDesc("gmt_create");
        //使用持久层方法实现分页查询
        baseMapper.selectPage(pageTeacher,queryWrapper);
    }
}
