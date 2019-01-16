/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ScheduleServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter13.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月16日 下午7:33:59   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service.impl;

import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**   
 * @ClassName:  ScheduleServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月16日 下午7:33:59   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class ScheduleServiceImpl {

	int count1 = 1;
	int count2 = 1;
	
	int count3 = 1;
	int count4 = 1;
	
	@Scheduled(fixedRate=1000)
	@Async
	public void job1() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"【job1】每秒钟执行一次，执行第【"+count1+"】次");
		count1++;
	}
	
	@Scheduled(fixedRate=1000)
	@Async
	public void job2() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"【job2】每秒钟执行一次，执行第【"+count2+"】次");
		count2++;
	}
	
	@Scheduled(initialDelay=3000, fixedRate=1000)
	@Async
	public void job3() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"【job3】每秒钟执行一次，执行第【"+count3+"】次");
		count3++;
	}
	//cron 秒 分 时  天 月  星期 年
	//11:00到11:59点每分钟执行一次
	@Scheduled(cron="0 * 11 * * ?")
	@Async
	public void job4() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"【job3】每秒钟执行一次，执行第【"+count4+"】次");
		count4++;
	}
}
