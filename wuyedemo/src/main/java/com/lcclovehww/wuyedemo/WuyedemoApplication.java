package com.lcclovehww.wuyedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.lcclovehww.wuyedemo.*"})
@MapperScan("com.lcclovehww.wuyedemo.mapper")
public class WuyedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuyedemoApplication.class, args);
    }

}
