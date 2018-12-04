/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  InWarehouseResService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月12日 上午10:02:52   
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
import com.tct.codec.protocol.pojo.InWarehouseResMessage;
import com.tct.db.dao.InWarehouseDao;
import com.tct.db.dao.MessageRecordsDao;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;
import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  InWarehouseResService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月12日 上午10:02:52   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("inWarehouseResService")
@Scope("prototype")
@Slf4j
public class InWarehouseResService implements TemplateService {

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
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		InWarehouseResMessage inWarehouseResMessage = (InWarehouseResMessage)msg;
		
		if(inWarehouseResMessage.getMessageBody().getState().equals(StringConstant.SUCCESS_NEW_STATE)){
			//更新数据
			MessageRecordsCustom mrc=mcDao.selectBySerlNum(inWarehouseResMessage.getSerialNumber());
			if (null==mrc) {
				log.info("该序列号不存在，请检查12号报文对应的11号报文序列号是否正确");
				return;
			}
			String gunId = mrc.getGunId();
			inDao.updateSelectiveByGunIdAndIngState(gunId);
		}else if(inWarehouseResMessage.getMessageBody().getState().equals(StringConstant.FAILURE_NEW_STATE)) {
			return;
/*			MessageRecordsCustom mrc=mcDao.selectBySerlNum(inWarehouseResMessage.getSerialNumber());
			String gunId = mrc.getGunId();
			inDao.updateSelectiveByGunIdAndRollbackState(gunId);*/
		}

	}

}
