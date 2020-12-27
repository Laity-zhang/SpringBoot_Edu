package com.zzn.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program : edu_parent
 * @ClassName : TeacherQuery
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-01 14:16
 */
@ApiModel(value = "Teacher查询对象",description = "讲师查询对象封装")
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔1高级讲师，2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间")
    private String begin;

    @ApiModelProperty(value = "查询结束时间")
    private String end;
}
