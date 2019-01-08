package com.lcclovehww.springboot.chapter8.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter8"})
@EnableMongoRepositories(basePackages="com.lcclovehww.springboot.chapter8.repository")
public class Chapter8Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter8Application.class, args);
	}

}

