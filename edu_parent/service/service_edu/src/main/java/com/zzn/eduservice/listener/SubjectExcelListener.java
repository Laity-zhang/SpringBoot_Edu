package com.zzn.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzn.eduservice.entity.EduSubject;
import com.zzn.eduservice.entity.excel.SubjectData;
import com.zzn.eduservice.service.EduSubjectService;
import com.zzn.servicebase.GuliException;

/**
 * @Program : edu_parent
 * @ClassName : SubjectExcelListener
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-28 16:55
 */

//由于SubjectExcelListener这个监听类不能交给Spring管理，
// 所以需要引入Service来实现数据库操作

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //引入service操作数据库
    public EduSubjectService subjectService;
    //subjectService的无参构造函数
    public SubjectExcelListener() {}
    //subjectService的有参构造函数，创建有参构造函数，传递service用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行读取Excel的内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //判断Excel里面的内容是否为空
        if (subjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }

        //一行一行读取数据，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //先判断一级分类是否重复，向数据库中添加一级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){//当没有相同的一级分类时添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //添加二级分类
        //判断二级分类是否重复
        String pid = existOneSubject.getId();//获取一级分类的id
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(),pid);
        if (existTwoSubject == null){//当没有相同的一级分类时添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    //判断一级分类是否重复的方法  不重复返回为空
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类是否重复的方法  不重复返回为空
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
