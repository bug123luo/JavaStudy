/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  StartStopSearchGunReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:50:09   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.AuthorizationResMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBodyGunInfo;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.codec.protocol.pojo.StartStopSearchGunReqMessage;
import com.tct.codec.protocol.pojo.StartStopSearchGunReqMessageBodyGunInfo;
import com.tct.db.dao.HeartbeatDao;
import com.tct.db.dao.HeartbeatDaoImpl;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  StartStopSearchGunReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:50:09   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("startStopSearchGunReqService")
@Scope("prototype")
public class StartStopSearchGunReqService implements TemplateService {
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private OutQueueSender outQueueSender;
	
	@Resource
	@Qualifier("outQueueDestination")
	private Destination outQueueDestination;
		
	@Autowired
	private HeartbeatDao hbDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		StartStopSearchGunReqMessage startStopSearchGunReqMessage = (StartStopSearchGunReqMessage)msg;
		
		String sessionToken = stringRedisTemplate.opsForValue().get(startStopSearchGunReqMessage.getUniqueIdentification());
		startStopSearchGunReqMessage.setSessionToken(sessionToken);
		
		SimpleReplyMessage simpleReplyMessage = constructReply(startStopSearchGunReqMessage);
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));
	}
	
	public SimpleReplyMessage constructReply(StartStopSearchGunReqMessage startStopSearchGunReqMessage) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(startStopSearchGunReqMessage,simpleReplyMessage);
		String gunList ="";
		for(StartStopSearchGunReqMessageBodyGunInfo gun:startStopSearchGunReqMessage.getMessageBody().getGunList()) {
			if(gunList.length()>1) {
				gunList+=StringConstant.MSG_BODY_SEPARATOR+gun.getGunMac();
			}else {
				gunList+=gun.getGunMac();
			}

		}
		String replyBody = StringConstant.MSG_BODY_PREFIX+startStopSearchGunReqMessage.getMessageBody().getCommand()
		+StringConstant.MSG_BODY_SEPARATOR+startStopSearchGunReqMessage.getMessageBody()
		+StringConstant.MSG_BODY_SEPARATOR+gunList
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}

}
