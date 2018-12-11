package com.lcclovehww.springboot.chapter4.main;

import java.lang.reflect.Proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lcclovehww.springboot.chapter4.aspect.MyAspect;
import com.lcclovehww.springboot.chapter4.intercept.MyInterceptor;
import com.lcclovehww.springboot.chapter4.proxy.ProxyBean;
import com.lcclovehww.springboot.chapter4.service.HelloService;
import com.lcclovehww.springboot.chapter4.service.impl.HelloServiceImpl;

@SpringBootApplication(scanBasePackages= {"com.lcclovehww.springboot.chapter4.aspect"})
public class Chapter4Application {

	
	@Bean(name="myAspect")
	public MyAspect initMyAspect() {
		return new MyAspect();
	}
	
	public static void main(String[] args) {

		SpringApplication.run(Chapter4Application.class, args);
		
/*		HelloService helloService = new HelloServiceImpl();
		HelloService proxy=(HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
		proxy.sayHello("zhangsan");
		System.out.println("\n####################name is null!!####################\n");
		proxy.sayHello(null);*/
	}
}
