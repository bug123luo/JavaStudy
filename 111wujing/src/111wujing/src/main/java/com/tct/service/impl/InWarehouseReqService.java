/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  InWarehouseReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:48:07   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.InWarehouseReqMessage;
import com.tct.db.dao.InWarehouseDao;
import com.tct.db.dao.InWarehouseDaoImpl;
import com.tct.db.dao.MessageRecordsDao;
import com.tct.db.dao.MessageRecordsDaoImpl;
import com.tct.db.mapper.WarehouseRecordsCustomMapper;
import com.tct.db.po.WarehouseRecords;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.jms.producer.OutQueueSender;

/**   
 * @ClassName:  InWarehouseReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:48:07   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("inWarehouseReqService")
@Scope("prototype")
public class InWarehouseReqService implements TemplateService {
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("jedisTemplate")
	private RedisTemplate<String,Map<String, ?>> jedisTemplate;
	
	@Resource
	private OutQueueSender outQueueSender;
	
	@Resource
	@Qualifier("outQueueDestination")
	private Destination outQueueDestination;
	
	@Autowired
	InWarehouseDao inDao;
	
	@Autowired
	MessageRecordsDao mcDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		InWarehouseReqMessage inWhReqMsg = (InWarehouseReqMessage)msg;
		
		//检查是否已经是入库中的记录，如果是，则不操作，直接返回，如果不是，则执行插入操作，并且将记录保存在数据库中
		WarehouseRecordsCustom whrc=inDao.selectByGunIdAndIngState(inWhReqMsg);
		if(whrc!=null) {
			return;
		}
		
		mcDao.insertSelective(inWhReqMsg);
		//int i = inDao.updateSelectiveByGunIdAndInState(inWhReqMsg);
		
		String sessionToken = stringRedisTemplate.opsForValue().get(inWhReqMsg.getUniqueIdentification());
		inWhReqMsg.setSessionToken(sessionToken);
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(inWhReqMsg));
	}

}
