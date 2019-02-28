package com.lcclovehww.springboot.chapter11.main;

import javax.annotation.PostConstruct;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages= "com.lcclovehww.springboot.chapter11")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter11.dao", annotationClass=Repository.class)
@EnableCaching
public class Chapter11Application {

 	@Autowired
	private RedisTemplate redisTemplate = null;

	@PostConstruct
	public void init() {
		initRedisTemplate();
	}
	
	private void initRedisTemplate() {
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter11Application.class, args);
	}

}
