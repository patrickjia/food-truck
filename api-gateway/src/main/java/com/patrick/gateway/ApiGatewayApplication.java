package com.patrick.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.patrick.gateway","com.patrick.common"},exclude = {DataSourceAutoConfiguration.class})
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiGatewayApplication.class); // 该设置方式
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }
}