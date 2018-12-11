/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyAspect.java   
 * @Package com.lcclovehww.springboot.chapter4.aspect   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午4:54:48   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import com.lcclovehww.springboot.chapter4.aspect.validator.UserValidator;
import com.lcclovehww.springboot.chapter4.aspect.validator.impl.UserValidatorImpl;

/**   
 * @ClassName:  MyAspect   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午4:54:48   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Aspect
public class MyAspect {
	
	@DeclareParents(value="com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl+",
			defaultImpl=UserValidatorImpl.class)
	public UserValidator userValidator;
	
	@Pointcut("execution(* com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	public void pointcut() {
		
	}

//	@Before("execution(* com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	@Before("pointcut()")
	public void before() {
		System.out.println("before ......");
	}
	
//	@After("execution(* com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	@After("pointcut()")
	public void after() {
		System.out.println("after ......");
	}
	
//	@AfterReturning("execution(* com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("afterReturning ......");
	}
	
//	@AfterThrowing("execution(* com.lcclovehww.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("afterThrowing ......");
	}
	
	@Around("pointcut()")
	public void around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("around before ......");
		jp.proceed();
		System.out.println("around after ......");
	}
}
