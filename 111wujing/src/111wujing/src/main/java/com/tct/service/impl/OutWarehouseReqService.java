/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  OutWarehouseReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:48:38   
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
import com.tct.codec.protocol.pojo.OutWarehouseReqMessage;
import com.tct.db.dao.OutWarehouseDao;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

import lombok.extern.slf4j.Slf4j;


/**   
 * @ClassName:  OutWarehouseReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:48:38   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Service("outWarehouseReqService")
@Scope("prototype")
public class OutWarehouseReqService implements TemplateService {

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
	OutWarehouseDao outWarehouseDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		OutWarehouseReqMessage oWReqMsg = (OutWarehouseReqMessage)msg;

		WarehouseRecordsCustom tempObj=outWarehouseDao.selectByGunIdAndState(oWReqMsg);
		if (tempObj!=null) {
			if(tempObj.getState().equals(StringConstant.OUT_WAREHOUSE_ING_STATE)) {
				//如果需要update更新领用时间和截止时间
				return;
			}else if(tempObj.getState().equals(StringConstant.OUT_WAREHOUSE_STATE) 
					|| tempObj.getState().equals(StringConstant.IN_WAREHOUSE_ING_STATE)){
				return;
			}

		}
				
		int i=outWarehouseDao.insertWarehouseRecords(oWReqMsg);
		if(i!=0) {
			String sessionToken =  stringRedisTemplate.opsForValue().get(oWReqMsg.getUniqueIdentification());
			oWReqMsg.setSessionToken(sessionToken);
			outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(oWReqMsg));
		}else {
			log.info("插入数据不成功，请检查插入数据!");
		}
	}
}
