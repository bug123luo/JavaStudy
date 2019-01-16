package com.lcclovehww.springboot.chapter13.main;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter13"})
@EnableScheduling
public class Chapter13Application {

	@Value("${rabbitmq.queue.msg}")
	private String msgQueueName=null;
	
	@Value("${rabbitmq.queue.user}")
	private String userQueueName=null;
	
	@Bean
	public Queue createQueueMsg() {
		return new Queue(msgQueueName,true);
	}
	
	@Bean
	public Queue createQueueUser() {
		return new Queue(userQueueName,true);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter13Application.class, args);
	}

}

