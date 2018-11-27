/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  CancelRecipientsGunResService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月8日 上午11:30:52   
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

import com.tct.codec.protocol.pojo.CancelRecipientsGunResMessage;
import com.tct.db.dao.MessageRecordsDao;
import com.tct.db.dao.MessageRecordsDaoImpl;
import com.tct.db.dao.OutWarehouseDao;
import com.tct.db.dao.OutWarehouseDaoImpl;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  CancelRecipientsGunResService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月8日 上午11:30:52   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

//10号报文处理
@Service("cancelRecipientsGunResService")
@Scope("prototype")
public class CancelRecipientsGunResService implements TemplateService {

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("jedisTemplate")
	private RedisTemplate<String,Map<String, ?>> jedisTemplate;
	
	@Autowired
	OutWarehouseDao outWarehouseDao;
	
	@Autowired
	MessageRecordsDao mRecDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		CancelRecipientsGunResMessage cRecGunResMsg = (CancelRecipientsGunResMessage)msg;
		MessageRecordsCustom mRecCustom=mRecDao.selectBySerlNum(cRecGunResMsg);
		
		if(cRecGunResMsg.getMessageBody().getState().equals(StringConstant.SUCCESS_NEW_STATE)) {
			outWarehouseDao.updateSelectiveByGunIdAndOutState(mRecCustom);
		}else {
			
		}
	}

}
