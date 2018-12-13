package com.lcclovehww.springboot.chapter5.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter5"})
//以下是使用JPA的添加注解，当使用mybatis的时候需要把这个注释掉
@EnableJpaRepositories(basePackages="com.lcclovehww.springboot.chapter5.dao")
@EntityScan(basePackages="com.lcclovehww.springboot.chapter5.pojo")
public class Chapter5Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter5Application.class, args);
	}

}

