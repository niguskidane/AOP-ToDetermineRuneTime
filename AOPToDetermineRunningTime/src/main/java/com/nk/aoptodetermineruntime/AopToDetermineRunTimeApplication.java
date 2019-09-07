package com.nk.aoptodetermineruntime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.nk"})
public class AopToDetermineRunTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopToDetermineRunTimeApplication.class, args);
    }

}
