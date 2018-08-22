package dudu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.CacheException;
import dudu.service.common.CacheService;
import redcosmos.danyuan.utils.redis_utils.RedisClientTemplate;


public class RedisCacheService implements CacheService {
	private static Logger log=LoggerFactory.getLogger(RedisCacheService.class);
	private RedisClientTemplate redisTemplate;
	public RedisCacheService(){

	}
	public RedisCacheService(RedisClientTemplate redisTemplate){
		this.redisTemplate=redisTemplate;
	}
	public RedisClientTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisClientTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Object set(String key, Object value) throws CacheException {
		log.debug(".set(key={}, value={})",
				key,
				value.getClass().getName());
		redisTemplate.setObject(key, value);
		return value;
	}

	@Override
	public Object set(String key, Object value, int timeToLiveSeconds)
			throws CacheException {
		log.debug(".set(key={}, value={},timeToLiveSeconds={})",new Object[]{
				key,
				value.getClass().getName(),timeToLiveSeconds});
		redisTemplate.setObject(key, value,timeToLiveSeconds);
		return value;
	}

	@Override
	public Object get(String key) throws CacheException {
		log.debug(".get(key={})",
				key);
		Object obj=redisTemplate.getObject(key);
		if(obj!=null){
			log.debug("hit redis cache key {} ",key);
		}else{
			log.debug("miss redis cache key {} ",key);
		}
		return obj;
	}

	@Override
	public void remove(String key) throws CacheException {
		log.debug(".remove(key={})",
				key);
		redisTemplate.removeKey(key);
	}

	@Override
	public boolean isKeyInCache(String key) {
		log.debug(".isKeyInCache(key={})",
				key);
		return redisTemplate.isKeyInCache(key);
	}
	public void setExprile(String key, int seconds) throws CacheException {
		log.debug(".setExprile(key={},seconds={})",
				key,seconds);
		redisTemplate.expire(key, seconds);
	}

}
