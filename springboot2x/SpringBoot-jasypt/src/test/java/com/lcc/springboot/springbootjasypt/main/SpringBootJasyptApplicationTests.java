package com.lcc.springboot.springbootjasypt.main;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootJasyptApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	StringEncryptor encryptor;
	
	@Test
	public void getPass() {
		String name = encryptor.encrypt("root");
		String password = encryptor.encrypt("nihao123");
        System.out.println("----------------"+name+"----------------"); 
        System.out.println("----------------"+password+"----------------"); 
	}

}
