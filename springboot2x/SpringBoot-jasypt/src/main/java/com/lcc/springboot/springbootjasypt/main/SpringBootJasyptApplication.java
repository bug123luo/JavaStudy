package com.lcc.springboot.springbootjasypt.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
//入口类添加该注解，开启属性自动解密功能
@EnableEncryptableProperties
public class SpringBootJasyptApplication {	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJasyptApplication.class, args);
	}

}
