package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import util.IdWorker;

/**
 * 数据库启动类 (项目入口)
 * @author BinPeng
 * @date 2020/5/6 10:22
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }
    @Bean//注入进来
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
