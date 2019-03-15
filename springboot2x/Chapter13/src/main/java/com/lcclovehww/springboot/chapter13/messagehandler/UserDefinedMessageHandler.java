/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserDefinedMessageHandler.java   
 * @Package com.lcclovehww.springboot.chapter13.messagehandler   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月14日 下午3:30:42   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.messagehandler;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter13.idgen.IdGen;
import com.lcclovehww.springboot.chapter13.mapper.PersonLocationMapper;
import com.lcclovehww.springboot.chapter13.pojo.IotJsonMsg;
import com.lcclovehww.springboot.chapter13.pojo.MsgBody;
import com.lcclovehww.springboot.chapter13.pojo.PersonLocation;
import com.lcclovehww.springboot.chapter13.util.IotStringToClass;

/**   
 * @ClassName:  UserDefinedMessageHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月14日 下午3:30:42   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

public class UserDefinedMessageHandler extends AbstractMessageHandler {

	@Resource
	PersonLocationMapper personLocationMapper;
	
	/**   
	 * <p>Title: handleMessageInternal</p>   
	 * <p>Description: </p>   
	 * @param message
	 * @throws Exception   
	 * @see org.springframework.integration.handler.AbstractMessageHandler#handleMessageInternal(org.springframework.messaging.Message)   
	 */
	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
    	//在当前位置处理函数转换
		//IotJsonMsg iotJsonMsg= IotStringToClass.changeToIotMsg(message.getPayload());

		MsgBody msgBody= IotStringToClass.changeToIotMsg(message.getPayload());
		
		if (null==msgBody) {
			return ;
		}
		
		PersonLocation entity = new PersonLocation();
		entity.setBaseStationId(msgBody.getBase());
		entity.setDeviceId(msgBody.getRepeater());
		entity.setPersonId(msgBody.getTag());
		entity.setStatus(msgBody.getStatus());
		
		//BeanUtils.copyProperties(msgBody,entity);
		
		//entity.setId(IdGen.get().nextId());
		
		personLocationMapper.insert(entity);
	}

}
