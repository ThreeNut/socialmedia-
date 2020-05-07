package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共异常处理
 * @author BinPeng
 * @date 2020/5/6 15:52
 */
@ControllerAdvice/*1.全局异常处理2.全局数据绑定3.全局数据预处理*/
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)//可以用来统一处理方法抛出的异常
    @ResponseBody
    public Result error(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
