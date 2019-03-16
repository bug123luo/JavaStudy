package com.lcclovehww.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lcclovehww.demo.exception.TokenException;
import com.lcclovehww.demo.pojo.RspMsg;
import com.lcclovehww.demo.pojo.TokenRspMsg;
import com.lcclovehww.demo.service.TokenStorageService;

import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController
@RequestMapping("/token")
@Slf4j
public class TokenController {
	 
	private static TokenStorageService storageService = new TokenStorageService();
	
	@PostMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
//	@ResponseBody
	public String updateToken(String access_token,String app_id) {
		
		log.debug("UC 调用 回调接口");
		log.debug("access_token="+access_token+" app_id="+app_id);
		RspMsg result = new RspMsg();
		
		String newToken = access_token;
		 if (StringUtils.isNotBlank(newToken)) {
		        storageService.storeNewAccessToken(newToken);
		        //result.setCode(0);
		        result.setMsg("success");
		 }else {
			 throw new TokenException(1,"no token");
			 /*result.setCode("1");
	         result.setMsg("no token");	*/
		}
		log.debug("uc 回调返回结果="+result.getMsg());
//		return result;
		return result.getMsg();
	}
	
	@GetMapping(value="/get", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
//	@ResponseBody
	public TokenRspMsg getToken() {
		log.debug("调用获取 Token 函数");
		String token = storageService.getAccessToken();
		TokenRspMsg tMsg = new TokenRspMsg();
		tMsg.setCode(0);
		tMsg.setMsg("success");
		tMsg.setToken(token);
		log.debug("token 函数返回="+tMsg);
		return tMsg;
	}
}
