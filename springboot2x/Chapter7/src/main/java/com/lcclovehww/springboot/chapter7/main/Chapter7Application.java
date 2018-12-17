package com.lcclovehww.springboot.chapter7.main;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter7")
public class Chapter7Application {
	
/*	@Autowired
	private RedisTemplate redisTemplate = null;

	@PostConstruct
	public void init() {
		initRedisTemplate();
	}
	
	private void initRedisTemplate() {
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter7Application.class, args);
	}

}

