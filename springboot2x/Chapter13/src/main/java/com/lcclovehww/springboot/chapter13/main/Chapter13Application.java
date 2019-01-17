package com.lcclovehww.springboot.chapter13.main;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
	@Value("${rabbitmq.queue.msg}")
	private String msgQueueName=null;
	
	@Value("${rabbitmq.queue.user}")
	private String userQueueName=null;
	
	@Bean
	public Queue createQueueMsg() {
		return new Queue(msgQueueName,true);
	}
	
	@Bean
	public Queue createQueueUser() {
		return new Queue(userQueueName,true);
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

