package com.lcclovehww.springboot.chapter3.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value= {"classpath:jdbc.properties"},ignoreResourceNotFound=true)
public class Chapter31Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter31Application.class, args);
	}
}
