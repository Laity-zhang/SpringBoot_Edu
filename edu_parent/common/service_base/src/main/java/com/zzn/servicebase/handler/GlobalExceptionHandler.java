package com.zzn.servicebase.handler;

import com.atguigu.commonutils.R;
import com.zzn.servicebase.GuliException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Program : edu_parent
 * @ClassName : GlobalExceptionHandler
 * @Description : 同一结果处理
 * @Author : zhangzhaonian
 * @Date: 2020-12-28 16:28
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
   @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    //异常处理方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了自定义异常");
    }

    //自定义异常处理方法
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
