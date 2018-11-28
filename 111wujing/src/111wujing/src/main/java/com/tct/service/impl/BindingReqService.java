/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BindingReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:45:29   
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
import com.tct.codec.protocol.pojo.BindingReqMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBodyGunInfo;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.db.dao.BindingGunDao;
import com.tct.db.dao.BindingGunDaoImpl;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  BindingReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:45:29   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("bindingReqService")
@Scope("prototype")
public class BindingReqService implements TemplateService {

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
	BindingGunDao bindingGunDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		BindingReqMessage bReqMsg = (BindingReqMessage)msg;
		
		//bindingGunDao.insertGugList(bReqMsg);
				
		String sessionToken = stringRedisTemplate.opsForValue().get(bReqMsg.getUniqueIdentification());
		bReqMsg.setSessionToken(sessionToken);
		
		SimpleReplyMessage simpleReplyMessage =constructReply(bReqMsg);
		
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));
	}
	
	public SimpleReplyMessage constructReply(BindingReqMessage bReqMsg) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(bReqMsg,simpleReplyMessage);
		String gunList ="";
		for(BindingReqMessageBodyGunInfo gun:bReqMsg.getMessageBody().getGunList()) {
			if(gunList.length()>1) {
				gunList+=StringConstant.MSG_BODY_SEPARATOR+gun.getGunType()+StringConstant.MSG_BODY_SEPARATOR
						+gun.getGunId()+StringConstant.MSG_BODY_SEPARATOR+gun.getGunModel();
			}else {
				gunList+=gun.getGunType()+StringConstant.MSG_BODY_SEPARATOR
						+gun.getGunId()+StringConstant.MSG_BODY_SEPARATOR+gun.getGunModel();
			}

		}
		
		String replyBody = StringConstant.MSG_BODY_PREFIX+bReqMsg.getMessageBody().getUserId()
		+StringConstant.MSG_BODY_SEPARATOR+gunList
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}

}
