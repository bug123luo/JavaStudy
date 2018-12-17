/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RedisController.java   
 * @Package com.lcclovehww.springboot.chapter7.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月17日 下午3:15:59   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter7.controller;


import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @ClassName:  RedisController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月17日 下午3:15:59   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	
	@RequestMapping("/stringAndHash")
	@ResponseBody
	public Map<String, Object> testStringAndHash(){
		
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("int_key", "1");
		stringRedisTemplate.opsForValue().set("int", "1");
		stringRedisTemplate.opsForValue().increment("int",1);
		Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();		
		jedis.decr("int");

		Map<String, String> hash = new HashMap<String,String>();
		hash.put("field1", "value1");
		hash.put("field2", "value2");
		
		stringRedisTemplate.opsForHash().putAll("hash", hash);
		stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
		BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
		hashOps.delete("field1","field2");
		hashOps.put("filed4", "value5");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
}
