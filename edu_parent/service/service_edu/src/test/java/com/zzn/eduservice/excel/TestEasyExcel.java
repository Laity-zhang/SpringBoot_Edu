package com.zzn.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program : edu_parent
 * @ClassName : TestEasyExcel
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-26 18:15
 */

public class TestEasyExcel {

    //创建方法返回list集合，用于向表中添加数据
    private static List<DataDemo> getData(){
        List<DataDemo> dataList = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            DataDemo data = new DataDemo();
            data.setSno(i);
            data.setSname("zzn" + i);
            dataList.add(data);
        }
        return dataList;
    }

    public static void main(String[] args) {
        //EasyExcel的写操作
        //1、定义文件的路径和文件名称
        //String fileName = "D:\\Desktop\\student.xlsx";
        //2、调用EasyExcel里面的方法实现写操作
        //write里面的第一个参数表示文件路径和名称，第二个参数表示实体类的class
        //EasyExcel.write(fileName,DataDemo.class).sheet("学生列表").doWrite(getData());

        //EasyExcel的读操作
        //1、定义文件的路径和文件名称
        String fileName = "D:\\Desktop\\student.xlsx";
        //2、调用EasyExcel里面的方法实现读操作
        EasyExcel.read(fileName,DataDemo.class,new Excellisten()).sheet().doRead();
    }

}
