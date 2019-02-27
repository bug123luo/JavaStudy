/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RedisConfig.java   
 * @Package com.lcclovehww.springboot.chapter7.config   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 上午9:37:06   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**   
 * @ClassName:  RedisConfig   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 上午9:37:06   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Configuration
public class RedisConfig {
	
	private  RedisConnectionFactory connectionFactory = null;
	
	
	@Bean(name="RedisConnectionFactory")
	public RedisConnectionFactory initRedisConnectionFactory() {
		if (this.connectionFactory !=null) {
			return this.connectionFactory;
		}
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);
		poolConfig.setMaxTotal(50);
		poolConfig.setMaxWaitMillis(2000);
		
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
		
		RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
		connectionFactory.setHostName("112.74.51.194");
		connectionFactory.setPort(6379);
		connectionFactory.setPassword("123456");
		connectionFactory.
		this.connectionFactory = connectionFactory;
		return connectionFactory;
	}
	
	
	@Bean(name="redisTemplate")
	public RedisTemplate<Object, Object> initRedisTemplate(){
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		//added by lcc 7-5modified
		RedisSerializer stringRedisSeralizer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringRedisSeralizer);
		redisTemplate.setValueSerializer(stringRedisSeralizer);
		redisTemplate.setHashKeySerializer(stringRedisSeralizer);
		redisTemplate.setHashValueSerializer(stringRedisSeralizer);
		//
		redisTemplate.setConnectionFactory(initRedisConnectionFactory());
		return redisTemplate;
	}
}
