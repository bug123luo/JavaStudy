/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ReportBulletNumberReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:49:34   
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
import com.tct.codec.protocol.pojo.ReportBulletNumberReqMessage;
import com.tct.codec.protocol.pojo.ReportBulletNumberResMessage;
import com.tct.codec.protocol.pojo.ReportBulletNumberResMessageBody;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.db.dao.GunBulletDao;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.MessageTypeConstant;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;



/**   
 * @ClassName:  ReportBulletNumberReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:49:34   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("reportBulletNumberReqService")
@Scope("prototype")
public class ReportBulletNumberReqService implements TemplateService {

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
	GunBulletDao gBdao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		ReportBulletNumberReqMessage reportBulletNumberReqMessage = (ReportBulletNumberReqMessage)msg;
		
		int i=0;
		i=gBdao.insertSelective(reportBulletNumberReqMessage);
		
		ReportBulletNumberResMessage message=constructRes(reportBulletNumberReqMessage);
		if(i==0) {
			message.getMessageBody().setState(StringConstant.FAILURE_OLD_STATE);
		}else {
			message.getMessageBody().setState(StringConstant.SUCCESS_OLD_STATE);

		}
		SimpleReplyMessage simpleReplyMessage =constructReply(message);
		
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));

	}

	public ReportBulletNumberResMessage constructRes(ReportBulletNumberReqMessage msg) {
		
		ReportBulletNumberResMessage resMsg = new ReportBulletNumberResMessage();
		ReportBulletNumberResMessageBody msgBody= new ReportBulletNumberResMessageBody();
		msgBody.setAuthCode(msg.getMessageBody().getAuthCode());
		
		String sessionToken = stringRedisTemplate.opsForValue().get(msg.getUniqueIdentification());
		resMsg.setDeviceType(msg.getDeviceType());
		resMsg.setFormatVersion(msg.getFormatVersion());
		resMsg.setMessageBody(msgBody);
		resMsg.setMessageType(MessageTypeConstant.MESSAGE24);
		resMsg.setSendTime(StringUtil.getDateString());
		resMsg.setSerialNumber(msg.getSerialNumber());
		resMsg.setSessionToken(sessionToken);
		resMsg.setUniqueIdentification(msg.getUniqueIdentification());
		
		return resMsg;
		
	}
	
	public SimpleReplyMessage constructReply(ReportBulletNumberResMessage message) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(message,simpleReplyMessage);
		String replyBody = StringConstant.MSG_BODY_PREFIX+message.getMessageBody().getState()
		+StringConstant.MSG_BODY_SEPARATOR+message.getMessageBody().getAuthCode()
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}
}
