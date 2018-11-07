/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AuthorizationReqService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月2日 上午11:33:30   
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
import com.tct.codec.protocol.pojo.AuthorizationReqMessage;
import com.tct.codec.protocol.pojo.AuthorizationResMessage;
import com.tct.codec.protocol.pojo.AuthorizationResMessageBody;
import com.tct.db.dao.AuthCodeDao;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;
import com.tct.jms.producer.OutQueueSender;
import com.tct.util.MessageTypeConstant;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

/**   
 * @ClassName:  AuthorizationReqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月2日 上午11:33:30   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("authorizationReqService")
@Scope("prototype")
public class AuthorizationReqService implements TemplateService {

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
	 * <p>Description: 接收腕表APP发过来的数据，到数据库中查询IMEI号,确认是否存在，如果存在将位置信息存入数据库中，最后返回腕表APP</p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		
		AuthorizationReqMessage arq = (AuthorizationReqMessage)msg;
		AuthorizationResMessageBody msgBody = new AuthorizationResMessageBody();
		
		//数据库中查询是否存在，如果腕表存在，响应报文
		AppCustom appCustom=authcodeDao.selectAppAllColumn(arq);
		if(appCustom!=null && (!appCustom.getAppImei().isEmpty())) {
			msgBody.setState(StringConstant.SUCCESS_OLD_STATE);
			msgBody.setAuthCode(StringConstant.AUTHCODE);
			msgBody.setIp(StringConstant.IP);
			msgBody.setPort(StringConstant.PORT);
		}else {
			msgBody.setState(StringConstant.FAILURE_OLD_STATE);
			msgBody.setAuthCode("");
			msgBody.setIp("");
			msgBody.setPort("");
		}
		
		AuthorizationResMessage arqRes =constructRes(arq, msgBody);
		outQueueSender.sendMessage(outQueueDestination, JSONObject.toJSONString(arqRes));
	}
	
	public AuthorizationResMessage constructRes(AuthorizationReqMessage arq, AuthorizationResMessageBody msgBody) {
		
		AuthorizationResMessage arqRes =new AuthorizationResMessage();
		arqRes.setDeviceType(arq.getDeviceType());
		arqRes.setFormatVersion(arq.getFormatVersion());
		arqRes.setMessageType(MessageTypeConstant.MESSAGE01);
		arqRes.setSendTime(StringUtil.getDateString());
		arqRes.setSerialNumber(arq.getSerialNumber());
		arqRes.setSessionToken(arq.getSessionToken());
		arqRes.setUniqueIdentification(arq.getUniqueIdentification());
		arqRes.setMessageBody(msgBody);
		
		return arqRes;
	}

}
