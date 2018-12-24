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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
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
		
		redisTemplate.opsForValue().set("key5", "value5");
		redisTemplate.opsForValue().set("int_key2", "1");
		stringRedisTemplate.opsForValue().set("int2", "1");
		stringRedisTemplate.opsForValue().increment("int2",1);
		Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();		
		jedis.decr("int2");

		Map<String, String> hash = new HashMap<String,String>();
		hash.put("field1", "value1");
		hash.put("field2", "value2");
		
		stringRedisTemplate.opsForHash().putAll("hash5", hash);
		stringRedisTemplate.opsForHash().put("hash5", "field3", "value3");
		BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash5");
		hashOps.delete("field1","field2");
		hashOps.put("filed4", "value5");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> testList(){
		stringRedisTemplate.opsForList().leftPushAll("list1", "v2","v4","v6","v8","v10");
		stringRedisTemplate.opsForList().rightPushAll("list2", "v1","v2","v3","v4","v5","v6");
		
		BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
		
		Object result1 = listOps.rightPop();
		Object result2 = listOps.index(1);
		listOps.leftPush("v0");
		Long size = listOps.size();
		
		List elements = listOps.range(0, size-2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/set")
	@ResponseBody
	public Map<String, Object> testSet(){
		stringRedisTemplate.opsForSet().add("set1", "v1","v2","v3","v4","v5");
		stringRedisTemplate.opsForSet().add("set2", "v2","v4","v6","v8");

		BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
		
		setOps.add("v6","v7");
		setOps.remove("v1","v7");
		Set set1 = setOps.members();
		Long size = setOps.size();
		//求交集
		Set inter = setOps.intersect("set2");
		//求交集，并且用新的集合inter保存
		setOps.intersectAndStore("set2", "inter");
		//求差集
		Set diff = setOps.diff("set2");
		//求差集，并且用新集合diff保存
		setOps.diffAndStore("set2", "diff");
		//求并集
		Set union = setOps.union("set2");
		//求并集，并且用新集合union保存
		setOps.unionAndStore("set2", "union");
	
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/zset")
	@ResponseBody
	public Map<String, Object> testZset(){
		
		Set<TypedTuple<String>> typedTupleSet = new HashSet<>();
		for(int i=1;i<=9;i++) {
			double score = i*0.1;
			TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value"+i, score);
			typedTupleSet.add(typedTuple);
		}
		
		stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
		
		BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zset1");
		//增加一个元素
		zSetOps.add("value10", 0.26);
		Set<String> setRange = zSetOps.range(1, 6);
		//按分数排序获取有序集合
		Set<String> setScore = zSetOps.rangeByScore(0.2, 0.6);
		Range range = new Range(); 
		//大于value3
		range.gt("value3");
		//range.gte("value3");
		//range.lt("value8");
		range.lte("value8");
		//按值排序，请注意这个排序是按字符串排序
		Set<String> setLex = zSetOps.rangeByLex(range);
		//删除元素
		zSetOps.remove("value9","value2");
		//求分数
		Double score = zSetOps.score("value8");
		//在下标区间下，按分数排序，同时返回value和score
		Set<TypedTuple<String>> rangeSet = zSetOps.rangeWithScores(1, 6);
		//在分数区间下，按分数排序， 同时返回value和score
		Set<TypedTuple<String>> scoreSet = zSetOps.reverseRangeByScoreWithScores(0.2, 0.6);
		//按照从大到小排序
		Set<String> reverseSet= zSetOps.reverseRange(2, 8);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/multi")
	@ResponseBody
	public Map<String, Object> testMulti(){
		redisTemplate.opsForValue().set("hww1", "hww1");
		
		List list =(List)redisTemplate.execute((RedisOperations operations)->{
			//设置要监控hww1
			operations.watch("hww1");
			//开启事务，在exec命令执行前，全部都只是进入队列
			operations.multi();
			operations.opsForValue().set("hww2", "hww2");
			//operations.opsForValue().increment("hww2", 1);
			Object value2 =operations.opsForValue().get("hww2");
			System.out.println("命令在队列,所以value 为null["+value2+"]");
			operations.opsForValue().set("hww3","hww3");
			Object value3 = operations.opsForValue().get("hww3");
			System.out.println("命令在队列，所以value为null["+value3+"]");
			return operations.exec();
		});
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
	
	@RequestMapping("/pipeline")
	@ResponseBody
	public Map<String, Object> testPipeline(){
		Long start = System.currentTimeMillis();
		List list = (List)redisTemplate.executePipelined((RedisOperations operations)->{
			for(int i=1; i<= 100000; i++) {
				operations.opsForValue().set("pipeline_"+i, "value_"+i);
				String value = (String) operations.opsForValue().get("pipeline+"+i);
				if(i == 100000) {
					System.out.println("命令只是进入队列，所以值为空["+value+"]");
				}
			}
			return null;
		});
		
		Long end = System.currentTimeMillis();
		System.out.println("耗时: "+(end-start)+"毫秒。 ");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
}
