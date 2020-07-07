package com.sun;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class BaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }

}