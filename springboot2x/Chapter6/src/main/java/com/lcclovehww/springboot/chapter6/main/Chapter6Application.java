package com.lcclovehww.springboot.chapter6.main;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter6")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter6",
		annotationClass =  Repository.class)
public class Chapter6Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter6Application.class, args);
	}
	
	@Autowired
	PlatformTransactionManager transactionManager = null;
	
	@PostConstruct
	public void viewTransactionManager() {
		System.out.println(transactionManager.getClass().getName());
	}

}

