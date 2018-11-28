/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SearchGunReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:49:52   
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
import com.tct.codec.protocol.pojo.AuthorizationResMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBodyGunInfo;
import com.tct.codec.protocol.pojo.SearchGunReqMessage;
import com.tct.codec.protocol.pojo.SearchGunReqMessageBodyGunInfo;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  SearchGunReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:49:52   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("searchGunReqService")
@Scope("prototype")
public class SearchGunReqService implements TemplateService {

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
		SearchGunReqMessage searchGunReqMessage = (SearchGunReqMessage)msg;
		
		String sessionToken = stringRedisTemplate.opsForValue().get(searchGunReqMessage.getUniqueIdentification());
		
		searchGunReqMessage.setSessionToken(sessionToken);
		
		SimpleReplyMessage simpleReplyMessage =constructReply(searchGunReqMessage);
		
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));
	}
	
	public SimpleReplyMessage constructReply(SearchGunReqMessage searchGunReqMessage) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(searchGunReqMessage,simpleReplyMessage);
		
		String gunList ="";
		for(SearchGunReqMessageBodyGunInfo gun:searchGunReqMessage.getMessageBody().getLostGunList()) {
			if(gunList.length()>1) {
				gunList+=StringConstant.MSG_BODY_SEPARATOR+gun.getGunMac()+StringConstant.MSG_BODY_SEPARATOR
						+gun.getGunId()+StringConstant.MSG_BODY_SEPARATOR+gun.getLostTime();
			}else {
				gunList+=gun.getGunMac()+StringConstant.MSG_BODY_SEPARATOR
						+gun.getGunId()+StringConstant.MSG_BODY_SEPARATOR+gun.getLostTime();
			}

		}
		String replyBody = StringConstant.MSG_BODY_PREFIX+searchGunReqMessage.getMessageBody().getReserve()
		+StringConstant.MSG_BODY_SEPARATOR+searchGunReqMessage.getMessageBody().getLo()
		+StringConstant.MSG_BODY_SEPARATOR+searchGunReqMessage.getMessageBody().getLa()
		+StringConstant.MSG_BODY_SEPARATOR+gunList
		+StringConstant.MSG_BODY_SEPARATOR+searchGunReqMessage.getMessageBody().getAuthCode()
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}

}
