package com.lcclovehww.springboot.chapter13.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter13"})
public class Chapter13Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter13Application.class, args);
	}

}

