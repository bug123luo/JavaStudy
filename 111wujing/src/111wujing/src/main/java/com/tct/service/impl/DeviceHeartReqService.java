/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DeviceHeartReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:46:45   
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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.AuthorizationResMessage;
import com.tct.codec.protocol.pojo.DeviceHeartReqMessage;
import com.tct.codec.protocol.pojo.DeviceHeartResMessage;
import com.tct.codec.protocol.pojo.DeviceHeartResMessageBody;
import com.tct.codec.protocol.pojo.SimpleReplyMessage;
import com.tct.db.dao.HeartbeatDao;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.MessageTypeConstant;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

/**   
 * @ClassName:  DeviceHeartReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:46:45   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("deviceHeartReqService")
@Scope("prototype")
public class DeviceHeartReqService implements TemplateService {

	@Resource
	private OutQueueSender outQueueSender;
	
	@Resource
	@Qualifier("outQueueDestination")
	private Destination outQueueDestination;
		
	@Autowired
	private HeartbeatDao heartbeatDao;
	

	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		DeviceHeartReqMessage deviceHeartReqMessage = (DeviceHeartReqMessage)msg;
		int i=0;
		i=heartbeatDao.intsertDeviceSelective(deviceHeartReqMessage);
		
		DeviceHeartResMessage dhrm = constructRes(deviceHeartReqMessage);
		
		if(0==i) {
			dhrm.getMessageBody().setState(StringConstant.FAILURE_NEW_STATE);
		}
		SimpleReplyMessage simpleReplyMessage =constructReply(dhrm);
		
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(simpleReplyMessage));
	}

	
	public DeviceHeartResMessage constructRes(DeviceHeartReqMessage msg) {
		DeviceHeartResMessage dhrm = new DeviceHeartResMessage();
		DeviceHeartResMessageBody messageBody = new DeviceHeartResMessageBody();
		messageBody.setAuthCode(msg.getMessageBody().getAuthCode());
		messageBody.setState(StringConstant.SUCCESS_NEW_STATE);
		dhrm.setDeviceType(msg.getDeviceType());
		dhrm.setFormatVersion(msg.getFormatVersion());
		dhrm.setMessageType(MessageTypeConstant.MESSAGE18);
		dhrm.setSendTime(StringUtil.getDateString());
		dhrm.setSerialNumber(msg.getSerialNumber());
		dhrm.setSessionToken(msg.getSessionToken());
		dhrm.setUniqueIdentification(msg.getUniqueIdentification());
		dhrm.setMessageBody(messageBody);
		return dhrm;
	}
	
	public SimpleReplyMessage constructReply(DeviceHeartResMessage dhrm) {
		
		SimpleReplyMessage simpleReplyMessage = new SimpleReplyMessage();
		BeanUtils.copyProperties(dhrm,simpleReplyMessage);
		String replyBody = StringConstant.MSG_BODY_PREFIX+dhrm.getMessageBody().getState()
		+StringConstant.MSG_BODY_SEPARATOR+dhrm.getMessageBody().getAuthCode()
		+StringConstant.MSG_BODY_SUFFIX;
		simpleReplyMessage.setMessageBody(replyBody);
		
		return simpleReplyMessage;
	}
}
