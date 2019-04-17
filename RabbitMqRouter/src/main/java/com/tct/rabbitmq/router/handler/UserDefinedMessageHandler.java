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
package com.tct.rabbitmq.router.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import com.alibaba.fastjson.JSON;
import com.tct.rabbitmq.router.config.RabbitMqConfig.MsgWriter;
import com.tct.rabbitmq.router.entity.HgPersonLocation;
import com.tct.rabbitmq.router.entity.MsgBody;
import com.tct.rabbitmq.router.entity.third.TctLocationMsg;
import com.tct.rabbitmq.router.entity.third.TctLocationMsgBody;
import com.tct.rabbitmq.router.mapper.HgPersonLocationMapper;
import com.tct.rabbitmq.router.util.IotStringToClass;
import org.springframework.stereotype.Component;


/**
 * @ClassName:  UserDefinedMessageHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月14日 下午3:30:42   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@Component
public class UserDefinedMessageHandler extends AbstractMessageHandler {

	@Value("${mqtt.send.topic}")
	private String sendTopic;

	@Value("${spring.mqtt.default.topic}")
	private String receTopic;
	
	@Autowired
	MsgWriter mqttGateway;
	
	@Resource
	HgPersonLocationMapper personLocationMapper;
	
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

		String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
		String type = topic.substring(topic.lastIndexOf("/")+1, topic.length());

		if(receTopic.equalsIgnoreCase(topic)){
			MsgBody msgBody= IotStringToClass.changeToIotMsg(message.getPayload());

			if (null==msgBody) {
				return ;
			}

			HgPersonLocation entity = new HgPersonLocation();
			entity.setBaseStationId(String.valueOf(msgBody.getBase()));
			entity.setDeviceCollectorId(String.valueOf(msgBody.getRepeater()));
//		entity.setDeviceId(String.valueOf(msgBody.getRepeater()));
//		entity.setPersonId(msgBody.getTag());
			entity.setDeviceCardId(msgBody.getTag());
			entity.setStatus(msgBody.getStatus());

			//BeanUtils.copyProperties(msgBody,entity);

			//entity.setId(IdGen.get().nextId());

			personLocationMapper.insert(entity);

			sendThirdTopic(msgBody);
		}


	}
	
   boolean sendThirdTopic(Object msgBody) {
	   if(msgBody instanceof MsgBody) {
		   MsgBody temp= (MsgBody)msgBody;
		   DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		   LocalDateTime time = LocalDateTime.now();
		   String localTime = df.format(time);
		   
		   TctLocationMsg tctLocationMsg = new TctLocationMsg();
		   TctLocationMsgBody tctLocationMsgBody = new TctLocationMsgBody();
		   tctLocationMsg.setMsgSendTime(localTime);
		   tctLocationMsg.setMsgSerial(localTime);
		   tctLocationMsgBody.setBase(String.valueOf(temp.getBase()));
		   tctLocationMsgBody.setRepeater(String.valueOf(temp.getRepeater()));
		   tctLocationMsgBody.setStatus(temp.getStatus());
		   tctLocationMsgBody.setTag(temp.getTag());
		   tctLocationMsg.setProjectId("1");//表示流花项目
		   tctLocationMsg.setMsgType("lo");
		   tctLocationMsg.setVersion("001");
		   tctLocationMsg.setMsgBody(tctLocationMsgBody);
		   
		   String jsonMsg = JSON.toJSONString(tctLocationMsg);
		   
		   mqttGateway.sendToMqtt(jsonMsg, sendTopic);
	   }
	   return true;
   }

}
