package com.zzn.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Program : edu_parent
 * @ClassName : SubjectData
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-28 16:43
 */

@Data
public class SubjectData {

    //一级标题
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //二级标题
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
