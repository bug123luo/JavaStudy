package com.tct.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tct.codec.impl.MessageBodyCodec;
import com.tct.codec.selector.MessageCodecSelector;
import com.tct.service.selector.ServiceSelector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage) {
	        TextMessage textMessage = (TextMessage) message;

	        log.info("-------------------------------------------------------------------");
			log.info("ConsumerMessageListener 接收消息");
			try {
				log.info(textMessage.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(null !=textMessage) {
				//编解码器选择器
				MessageCodecSelector messageCodecSelector = new MessageCodecSelector();			
				MessageBodyCodec messageBodyCodec = null;
				JSONObject json = null;
				try {
					//log.info(textMessage.getText());
					String[] temps=textMessage.getText().split(",");
					for(String str1:temps) {
						log.info(str1);
					}
			        log.info("-------------------------------------------------------------------");
			        json= JSONObject.parseObject(textMessage.getText());
			        //log.info(json.toString());
					messageBodyCodec = messageCodecSelector.getMessageDecode(json);
				} catch (Exception e2) {
					log.debug(e2+"消息解码器不存在");
				}
				
				//业务处理选择器
				ServiceSelector serviceSelector = new ServiceSelector();
				serviceSelector.handlerService(messageBodyCodec, json);
			}
		}else {
			log.info("收到非TextMessage消息类型");
		}
	}
}
