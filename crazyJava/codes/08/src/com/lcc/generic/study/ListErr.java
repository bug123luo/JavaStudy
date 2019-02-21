/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ListErr.java   
 * @Package com.lcc.generic.study   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月21日 下午5:47:45   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.generic.study;

import java.util.List;
import java.util.ArrayList;

/**   
 * @ClassName:  ListErr   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月21日 下午5:47:45   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ListErr {

	public static void main(String[] args) {
		List strList =new ArrayList();
		strList.add("Struts2权威指南");
		strList.add("基于J2EE的AJAX宝典");
		strList.add("轻量级J2EE企业应用实战");
		//
		strList.add(5);
		for (int i = 0; i < strList.size(); i++) {
			String str= (String)strList.get(i);
		}
	}
}
