/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  WatchHeartReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:51:07   
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
import com.tct.codec.protocol.pojo.WatchHeartReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartResMessage;
import com.tct.codec.protocol.pojo.WatchHeartResMessageBody;
import com.tct.db.dao.HeartbeatDao;
import com.tct.db.dao.HeartbeatDaoImpl;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.MessageTypeConstant;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

/**   
 * @ClassName:  WatchHeartReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:51:07   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("watchHeartReqService")
@Scope("prototype")
public class WatchHeartReqService implements TemplateService {

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
		WatchHeartReqMessage wHRMsg = (WatchHeartReqMessage)msg;

		//插入随行设备APP发过来的随行状态上报周期数据
		hbDao.insertAppHeartbeatSelective(wHRMsg);
		
		WatchHeartResMessage wHResMsg = constructRes(wHRMsg);
		
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(wHResMsg));
	}

	public WatchHeartResMessage constructRes(WatchHeartReqMessage msg) {
		
		WatchHeartResMessage wHResMsg =new WatchHeartResMessage();
		WatchHeartResMessageBody msgBody = new WatchHeartResMessageBody();
		msgBody.setAuthCode(wHResMsg.getMessageBody().getAuthCode());
		msgBody.setState(StringConstant.SUCCESS_NEW_STATE);
		wHResMsg.setMessageBody(msgBody);
		wHResMsg.setDeviceType(msg.getDeviceType());
		wHResMsg.setFormatVersion(msg.getFormatVersion());
		wHResMsg.setMessageType(MessageTypeConstant.MESSAGE16);
		wHResMsg.setSendTime(StringUtil.getDateString());
		wHResMsg.setSerialNumber(msg.getSerialNumber());
		wHResMsg.setSessionToken(msg.getSessionToken());
		wHResMsg.setUniqueIdentification(msg.getUniqueIdentification());
		return wHResMsg;
	}
}
