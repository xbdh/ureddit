package com.jicyu.ureddit.subreddit.provider;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

@ComponentScan(basePackages = {"com.jicyu.ureddit.subreddit.provider","com.jicyu.ureddit.common.logging.rpc"})
public class SubredditProviderApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SubredditProviderApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
//        SpringApplication.run(SubredditProviderApplication.class,args);

    }
}