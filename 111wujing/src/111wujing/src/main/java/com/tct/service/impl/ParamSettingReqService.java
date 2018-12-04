/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ParamSettingReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:48:59   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import javax.jms.Destination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.ParamSettingReqMessage;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  ParamSettingReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:48:59   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("paramSettingReqService")
@Scope("prototype")
public class ParamSettingReqService implements TemplateService {

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
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		ParamSettingReqMessage paramSettingReqMessage = (ParamSettingReqMessage)msg;
		
		String sessionToken=stringRedisTemplate.opsForValue().get(paramSettingReqMessage.getUniqueIdentification());
		paramSettingReqMessage.setSessionToken(sessionToken);
		SimpleReplyMessage simpleReplyMessage =constructReply(paramSettingReqMessage);
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));
	}
	
	public SimpleReplyMessage constructReply(ParamSettingReqMessage paramSettingReqMessage) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(paramSettingReqMessage,simpleReplyMessage);
		String replyBody = StringConstant.MSG_BODY_PREFIX+paramSettingReqMessage.getMessageBody().getPowerAlarmLevel()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getTransmittingPower()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getBroadcastInterval()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getConnectionInterval()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getConnectionTimeout()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getSoftwareDeviceVersion()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getHeartbeat()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getPowerSampling()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getSystemTime()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getMatchTime()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getPositioningInterval()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getSafeCode()
		+StringConstant.MSG_BODY_SEPARATOR+paramSettingReqMessage.getMessageBody().getAuthCode()
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}

}
