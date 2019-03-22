package com.tct.rabbitmq.router.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.tct.rabbitmq.router"})
@MapperScan("com.tct.rabbitmq.router.mapper")
public class RabbitMqRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqRouterApplication.class, args);
	}

}
