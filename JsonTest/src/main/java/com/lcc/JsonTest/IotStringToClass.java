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
package com.lcc.JsonTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**   
 * @ClassName:  IotStringToClass   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月5日 下午4:57:18   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

public class IotStringToClass {
	
	public static IotJsonMsg changeToIotMsg(String msg) {
		//JSONObject msgJson = JSON.parseObject(msg);
		//return JSON.toJavaObject(msgJson, IotJsonMsg.class);
		return JSON.parseObject(msg, IotJsonMsg.class);
	}
}
