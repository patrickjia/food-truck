package com.patrick.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 食品车业务服务-启动入口类
 */
@SpringBootApplication(scanBasePackages = {"com.patrick.food","com.patrick.common"})
@MapperScan(basePackages = "com.patrick.food.mapper")
@EnableTransactionManagement
public class FoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class,args);
    }
}