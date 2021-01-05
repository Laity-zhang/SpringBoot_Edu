package com.zzn.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzn.eduservice.entity.EduSubject;
import com.zzn.eduservice.entity.excel.SubjectData;
import com.zzn.eduservice.entity.subject.OneSubject;
import com.zzn.eduservice.entity.subject.TwoSubject;
import com.zzn.eduservice.listener.SubjectExcelListener;
import com.zzn.eduservice.mapper.EduSubjectMapper;
import com.zzn.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzn.servicebase.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //课程分类列表（树形结构显示）
    @Override
    public List<OneSubject> getSubject() {
        //1、查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2、查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //3、创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //4、封装一级分类
        //查询处所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，封装到要求的list集合里面
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //得到oneSubject里面每个eduSuubject对象
            EduSubject eduSubject = oneSubjectList.get(i);

            //把eduSubject里面的值取出来放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            //oneSubject.setId(eduSubject.getId());
            //oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject,oneSubject);//上面两句代码和这一句代码功能一样

            //再把多个OneSubject房到finalSubjectList里面
            finalSubjectList.add(oneSubject);

            //创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            //遍历二级分类list集合
            for (int j = 0; j < twoSubjectList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类的parent_id和一级分类的id是否一致
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubject复制到TwoSubject里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    //把多个TwoSubject放到twoFinalSubjectList里面
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            // 把一级分类下面的所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);


        }
        return finalSubjectList;
    }
}
