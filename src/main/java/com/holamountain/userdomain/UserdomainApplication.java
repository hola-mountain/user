package com.holamountain.userdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserdomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserdomainApplication.class, args);
    }

}
