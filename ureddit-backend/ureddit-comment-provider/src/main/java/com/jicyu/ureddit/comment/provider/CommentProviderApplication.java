package com.jicyu.ureddit.comment.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jicyu.ureddit.comment.provider","com.jicyu.ureddit.common.logging.rpc"})
public class CommentProviderApplication {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(CommentProviderApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }
}