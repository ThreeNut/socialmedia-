package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author BinPeng
 * @date 2020/5/28 22:41
 */

@FeignClient("TENSQUARE-BASE") //调用另一个模块
public interface BaseClient {

    //例如调用base里面的label 的findById方法
    @GetMapping(value = "/label/{id}")//注意路径
    public Result findById(@PathVariable("id") String id);
}
