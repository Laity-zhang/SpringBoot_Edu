package com.zzn.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Program : edu_parent
 * @ClassName : DataDemo
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-26 18:13
 */
@Data
public class DataDemo {
    @ExcelProperty(value = "学生学号",index = 0)//设置Excel的表头
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
