/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RegistReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:49:18   
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
import com.tct.codec.protocol.pojo.BindingReqMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBody;
import com.tct.codec.protocol.pojo.RegistReqMessage;
import com.tct.codec.protocol.pojo.RegistReqMessageBody;
import com.tct.codec.protocol.pojo.RegistResMessage;
import com.tct.codec.protocol.pojo.RegistResMessageBody;
import com.tct.db.dao.AuthCodeDao;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.MessageTypeConstant;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

/**   
 * @ClassName:  RegistReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:49:18   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("registReqService")
@Scope("prototype")
public class RegistReqService implements TemplateService {

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
	AuthCodeDao authcodeDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		RegistReqMessage regReqMsg = (RegistReqMessage)msg;
		
		String watchMac = regReqMsg.getMessageBody().getWatchMac();
		String imei = regReqMsg.getMessageBody().getImei();
		String phone = regReqMsg.getMessageBody().getPhone();

		AppCustomQueryVo appCustomQueryVo = new AppCustomQueryVo();
		AppCustom appCustomQuery = new AppCustom();
		appCustomQuery.setAppImei(imei);
		appCustomQuery.setAppMac(watchMac);
		appCustomQuery.setAppPhone(phone);
		appCustomQueryVo.setAppCustom(appCustomQuery);
		AppCustom appCustom = authcodeDao.selectAppAllColumn(appCustomQueryVo);
		
		
		RegistResMessageBody regResBody = new RegistResMessageBody(); 
		if(appCustom!=null && (!appCustom.getAppReadableCode().isEmpty())){
			regResBody.setReadableCode(appCustom.getAppReadableCode());
			regResBody.setState(StringConstant.SUCCESS_OLD_STATE);
			stringRedisTemplate.opsForValue().set(regReqMsg.getUniqueIdentification(), regReqMsg.getSessionToken());
			jedisTemplate.opsForHash().put(StringConstant.SESSION_DEVICE_HASH, regReqMsg.getSessionToken(), regReqMsg.getUniqueIdentification());
		}else {
			regResBody.setReadableCode("");
			regResBody.setState(StringConstant.FAILURE_OLD_STATE);			
		}
		
		RegistResMessage regResMsg = new RegistResMessage();
		regResMsg.setDeviceType(regReqMsg.getDeviceType());
		regResMsg.setFormatVersion(regReqMsg.getFormatVersion());
		regResMsg.setMessageBody(regResBody);
		regResMsg.setMessageType(MessageTypeConstant.MESSAGE04);
		regResMsg.setSendTime(StringUtil.getDateString());
		regResMsg.setSerialNumber(regReqMsg.getSerialNumber());
		regResMsg.setSessionToken(regReqMsg.getSessionToken());
		regResMsg.setUniqueIdentification(regReqMsg.getUniqueIdentification());
		//发送04好报文给用户
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(regResMsg));
				
	}

}
