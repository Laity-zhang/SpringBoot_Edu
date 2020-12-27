package com.zzn.eduservice.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzn.eduservice.entity.EduTeacher;
import com.zzn.eduservice.entity.vo.TeacherQuery;
import com.zzn.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangzhaonian
 * @since 2020-11-29
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin//解决浏览器跨域问题
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有讲师信息
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return R.ok().data("items",eduTeachers);
    }

    //逻辑删除讲师信息
    @ApiOperation(value = "逻辑删除讲师信息")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    //分页查询讲师信息
    @ApiOperation(value = "分页查询讲师信息")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageList( @ApiParam(name = "current", value = "当前页码", required = true)
                       @PathVariable Long current,
                       @ApiParam(name = "limit", value = "每页记录数", required = true)
                       @PathVariable Long limit){
        //创建Page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页，并将结果封装在pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);

        Long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        //Map map = new HashMap();
        //map.put("total",total);
        //map.put("rows",records);
        //return R.ok().data(map);

        return R.ok().data("total",total).data("rows",records);
    }

    //按组合条件分页查询讲师信息
    @ApiOperation(value = "按条件分页查询信息")
    @PostMapping("pageQuery/{current}/{limit}")
    public R pageQuery(
                       @ApiParam(name = "current",value = "当前页码",required = true)
                       @PathVariable Long current,

                       @ApiParam(name = "limit",value = "每页记录数",required = true)
                       @PathVariable Long limit,

                       @ApiParam(name = "teacherQuer",value = "查询对象",required = false)
                       @RequestBody TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //实现多条件查询
        eduTeacherService.pageQuery(pageTeacher,teacherQuery);
        List<EduTeacher> records = pageTeacher.getRecords();

        long total = pageTeacher.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    //添加讲师
    @ApiOperation(value = "添加讲师信息")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    //根据ID查询讲师信息

    @ApiOperation(value = "根据ID查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(
            @ApiParam(name = "id",value = "讲师ID",required =true)
            @PathVariable String id){

        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("items",eduTeacher);
    }

    //修改讲师信息
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("editTeacher")
    public R updateTeacherById(
            @ApiParam(name = "eduTeacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher eduTeacher){
        
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

}

