package com.lcclovehww.springboot.chapter7.main;

import javax.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;
import com.lcclovehww.springboot.chapter7.config.RedisConfig;
import com.lcclovehww.springboot.chapter7.listener.RedisMessageListener;


@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter7")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter7", annotationClass= Repository.class)
@EnableCaching
public class Chapter7Application {
	
/*	@Autowired
	private RedisTemplate redisTemplate = null;*/
	
	@Autowired
	private RedisConnectionFactory connectionFactory = null;
	
/*	@Autowired
	private MessageListener redisMsgListener = null;
	
	private ThreadPoolTaskScheduler taskScheduler = null;*/
	
 	//7-11 added by lcc 20181224
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
	//end 7-11 added by lcc 20181224
	
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter7Application.class, args);
		
/*		ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
		RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
		redisTemplate.opsForValue().set("lcc2", "lcc2");
		redisTemplate.opsForHash().put("hash2", "field2", "hvalue2");*/
	}
	
/*	//added by lcc 20181224 7-8
	public void useRedisCallback(RedisTemplate redisTemplate) {
		redisTemplate.execute(new RedisCallback() {

			@Override
			public Object doInRedis(RedisConnection rc) throws DataAccessException {
				rc.set("key3".getBytes(), "value3".getBytes());
				rc.hSet("hash3".getBytes(), "filed3".getBytes(), "value3".getBytes());
				return null;
			}
		});
	}

	public void useSessionCallback(RedisTemplate redisTemplate) {
		redisTemplate.execute(new SessionCallback() {

			@Override
			public Object execute(RedisOperations ro) throws DataAccessException {
				ro.opsForValue().set("key4", "value4");
				ro.opsForHash().put("hash4", "field4", "hvalue4");
				return null;
			}
		
		});
	}
	//end added by lcc 20181224 7-8
*/
/*	@Bean
	public ThreadPoolTaskScheduler initTaskScheduler() {
		if(taskScheduler !=null) {
			return taskScheduler;
		}
		
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}
	
	@Bean
	public RedisMessageListenerContainer initRedisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory);
		container.setTaskExecutor(initTaskScheduler());
		Topic topic = new ChannelTopic("topic1");
		container.addMessageListener(redisMsgListener, topic);
		return container;
	}*/
}

