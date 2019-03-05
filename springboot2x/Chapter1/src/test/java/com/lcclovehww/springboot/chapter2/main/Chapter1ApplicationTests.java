package com.lcclovehww.springboot.chapter2.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter1ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void strToJson() {
		JSONObject testjson = new JSONObject();
		testjson.put("version", "001");
		System.out.println(testjson.toJSONString());
		String msg=testjson.toString();
		System.out.println(msg);
		//IotJsonMsg iotJsonMsg = IotStringToClass.changeToIotMsg(msg);
		
	}
}
