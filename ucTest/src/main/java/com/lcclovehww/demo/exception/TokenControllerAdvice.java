package com.lcclovehww.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.lcclovehww.demo.exception.TokenException;

@ControllerAdvice(basePackages= {"com.lcclovehww.demo.controller.*"},annotations= {Controller.class,RestController.class})
public class TokenControllerAdvice {

	@ExceptionHandler(value=TokenException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> exception(HttpServletRequest request, TokenException ex){
		Map<String, Object> msgMap = new HashMap<>();
		msgMap.put("code", ex.getCode());
		msgMap.put("message", ex.getMsg());
		return msgMap;
	}
	
}
