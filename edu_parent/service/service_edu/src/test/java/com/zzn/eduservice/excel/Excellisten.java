package com.zzn.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Program : edu_parent
 * @ClassName : Excellisten
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-26 18:48
 */

public class Excellisten extends AnalysisEventListener<DataDemo> {
    //一行一行读取数据
    @Override
    public void invoke(DataDemo data, AnalysisContext analysisContext) {
        System.out.println("*****"+data);
    }

    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
