package com.lcclovehww.springboot.chapter9.main;

import org.springframework.stereotype.Repository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter9")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter9",annotationClass=Repository.class)
public class Chapter9Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter9Application.class, args);
	}

}
