package com.lcclovehww.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.lcclovehww.demo.*"})
public class UcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcTestApplication.class, args);
	}

}
