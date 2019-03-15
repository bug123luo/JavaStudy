/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  IotStringToClass.java   
 * @Package com.lcclovehww.springboot.chapter13.util   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月5日 下午4:57:18   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcclovehww.springboot.chapter13.pojo.MsgBody;

import lombok.extern.slf4j.Slf4j;


/**   
 * @ClassName:  IotStringToClass   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月5日 下午4:57:18   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */


@Slf4j
public class IotStringToClass {
	
/*	public static IotJsonMsg changeToIotMsg(Object object) {
		JSONObject msgJson = JSON.parseObject((String) object);
		return JSON.toJavaObject(msgJson, IotJsonMsg.class);
	}*/
	
	public static MsgBody changeToIotMsg(Object object) {
		
		JSONObject msgJson =null;
		
		String tempStr11 = "{\"base\":21,\"repeater\":20,\"tag\":\"adsfsd\",\"status\":\"on\"}";
		String tempStr = (String)object;
		System.out.println(tempStr11);
		System.out.println(tempStr);
		
		try {
			msgJson = JSON.parseObject(tempStr);
		} catch (Exception e) {
			//log.debug("输入数据格式不正确，请确认正确的输入格式 ！ ");
			System.out.println("输入数据格式不正确，请确认正确的输入格式 ！ ");
			return null;
		}
		
		return JSON.toJavaObject(msgJson, MsgBody.class);
	}
}
