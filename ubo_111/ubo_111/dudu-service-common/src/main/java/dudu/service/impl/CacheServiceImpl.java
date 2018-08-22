/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.CacheException;
import dudu.service.common.CacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class CacheServiceImpl implements CacheService {
	
	private static Logger LOG=LoggerFactory.getLogger(CacheServiceImpl.class);
	
	private Cache cache;
	
	public CacheServiceImpl(Cache cache) {
		this.cache = cache;
	}

	public Object set(String key, Object value) throws CacheException {
		
		LOG.debug(".set(key={}, value={})",
			key,
			value.getClass().getName());
		
		Element prev = null;
		
		try {
			Element ele = cache.get(key);
			if (ele == null) {
				LOG.debug("{} miss ehcache!", key);
				ele = new Element(key, value);
				cache.put(ele);
			} else {
				prev = ele;
				LOG.debug("{} hit ehcache!", key);
				cache.remove(key);
				
				ele = new Element(key, value);
				cache.put(ele);
			}
		} catch(Exception e) {
			throw new CacheException(e);
		}
		
		return prev;
	}
	
	public Object set(String key, Object value, int timeToLiveSeconds) throws CacheException {
		
		LOG.debug(String.format(
			".set(key=%s, value=%s, timeToLiveSeconds=%d)",
			key,
			value.getClass().getName(),
			timeToLiveSeconds));
		
		Element prev = null;
		try {
			Element ele = cache.get(key);
			if (ele == null) {
				LOG.debug("{} miss ehcache!", key);
				ele = new Element(key, value);
				ele.setTimeToLive(timeToLiveSeconds);
				cache.put(ele);
			} else {
				prev = ele;
				LOG.debug("{} hit ehcache!", key);
				cache.remove(key);
				
				ele = new Element(key, value);
				ele.setTimeToLive(timeToLiveSeconds);
				cache.put(ele);
			}
		} catch(Exception e) {
			throw new CacheException(e);
		}
		
		return prev;
	}

	public Object get(String key) throws CacheException {
		
		LOG.debug(".get(key={})", key);

		Element ele = null;
		try {
			ele = cache.get(key); 
		} catch(Exception e) {
			throw new CacheException(e);
		}
		
		if (ele != null) {
			LOG.debug("{} hit ehcache!", key);
			return ele.getObjectValue();
		} else {
			LOG.debug("{} miss ehcache!", key);
			return null;
		}
	}

	public void remove(String key) throws CacheException {
		
		LOG.debug(".remove({})", key);
		
		try {
			cache.remove(key);
		} catch(Exception e) {
			throw new CacheException(e);
		}
	}
	
	public boolean isKeyInCache(String key) {
		
		LOG.debug(".isKeyInCache({})", key);
		
		return cache.isKeyInCache(key);
	}
	
}
