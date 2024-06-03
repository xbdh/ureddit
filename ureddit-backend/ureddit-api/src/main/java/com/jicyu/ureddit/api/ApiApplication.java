package com.jicyu.ureddit.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.jicyu.ureddit.api","com.jicyu.ureddit.common.logging.api"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApiApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);

    }
}