package com.lcclovehww.springboot.chapter13.main;


import static org.hamcrest.CoreMatchers.nullValue;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter13"})
//@EnableScheduling
/*public class Chapter13Application extends WebSecurityConfigurerAdapter{
*/	
public class Chapter13Application {
	
	private String='{"base":11,"repeater":20,"tag":"a6fbd","status":"on"}';
	
	@Value("${rabbitmq.queue.msg}")
	private String msgQueueName=null;
	
	@Value("${rabbitmq.queue.user}")
	private String userQueueName=null;
	
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
	public Queue createQueueMsg() {
		return new Queue(msgQueueName,true);
	}
	
	@Bean
	public Queue createQueueUser() {
		return new Queue(userQueueName,true);
	}

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
                .transform(p -> p + ", received from MQTT")
                .handle(logger())
                .get();
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
	
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
		.withUser("user1")
		.password("$2a$10$7njFQKL2WV862XP6Hlyly.F0lkSHtOOQyQ/rlY7Ok26h.gGZD4IqG").roles("USER").and()
		.withUser("user2")
		.password("$2a$10$Q2PwvWNpog5sZX583LuQfet.y1rfPMsqtrb7IjmvRn7Ew/wNUjVwS").roles("ADMIN").and()
		.withUser("user3")
		.password("$2a$10$GskYZT.34BdhmEdOlAS8Re7D73RprpGN0NjaiqS2Ud8XdcBcJck4u").roles("USER");
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter13Application.class, args);
	}

}

