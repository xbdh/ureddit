package com.jicyu.ureddit.subscription.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jicyu.ureddit.subscription.provider","com.jicyu.ureddit.common.logging.rpc"})
public class SubscriptionProviderApplication {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SubscriptionProviderApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }
}