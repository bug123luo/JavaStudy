/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RabbitMqConfig.java   
 * @Package com.tct.rabbitmq.router.config   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月22日 下午6:03:11   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.rabbitmq.router.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.tct.rabbitmq.router.handler.UserDefinedMessageHandler;



/**   
 * @ClassName:  RabbitMqConfig   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月22日 下午6:03:11   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Configuration
public class RabbitMqConfig {

	
	@Value("${spring.mqtt.url}")
	private String mqttUrl=null;
	
	@Value("${spring.mqtt.username}")
	private String username=null;
	
	@Value("${spring.mqtt.password}")
	private String password=null;
	
	@Value("${spring.mqtt.default.topic}")
	private String topic=null;
	
	@Value("${spring.mqtt.client.id}")
	private String comsumerClientId=null;
	
	@Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        String[] urlStrings=mqttUrl.split(",");
        options.setPassword(password.toCharArray());
        options.setUserName(username);
        options.setServerURIs(urlStrings);
        factory.setConnectionOptions(options);
        return factory;
    }
	
	@Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
                //.transform(p -> p + ", received from MQTT")
        		.transform(p -> p)
                //.handle(logger())
                .handle(handler())
                .get();
    }

	private UserDefinedMessageHandler handler() {
		UserDefinedMessageHandler userDefinedMessageHandler = new UserDefinedMessageHandler();
		return userDefinedMessageHandler;
	}
	
    private LoggingHandler logger() {
        LoggingHandler loggingHandler = new LoggingHandler("INFO");
        loggingHandler.setLoggerName("lccRceiver");
        return loggingHandler;
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(comsumerClientId,
                mqttClientFactory(), topic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }
    
    @Bean
    public IntegrationFlow mqttOutFlow() {
        //console input
//        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
//                e -> e.poller(Pollers.fixedDelay(1000)))
//                .transform(p -> p + " sent to MQTT")
//                .handle(mqttOutbound())
//                .get();
        return IntegrationFlows.from(outChannel())
                .handle(mqttOutbound())
                .get();
    }
    
    @Bean
    public MessageChannel outChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topic);
        return messageHandler;
    }
    
    @MessagingGateway(defaultRequestChannel = "outChannel")
    public interface MsgWriter {
        void write(String note);
    }
}
