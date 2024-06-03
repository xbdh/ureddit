package com.jicyu.ureddit.vote.provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jicyu.ureddit.vote.provider","com.jicyu.ureddit.common.logging.rpc"})
public class VoteProviderApplication {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(VoteProviderApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }
}