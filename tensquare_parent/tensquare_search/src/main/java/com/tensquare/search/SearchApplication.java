package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 *
 * 搜索启动类
 * @author BinPeng
 * @date 2020/5/14 10:27
 */
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }


    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
