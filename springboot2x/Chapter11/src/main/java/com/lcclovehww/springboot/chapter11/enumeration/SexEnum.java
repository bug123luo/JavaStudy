/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SexEnum.java   
 * @Package com.lcclovehww.springboot.chapter54.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月28日 上午10:44:22   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter11.enumeration;

import lombok.Getter;
import lombok.Setter;

/**   
 * @ClassName:  SexEnum   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月28日 上午10:44:22   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */


public enum SexEnum {
	
	MALE(0,"男"),
	FEMALE(1,"女");

	@Getter
	@Setter
	private int code;
	@Getter
	@Setter
	private String name;
	
	SexEnum(int id, String name){
		this.code = id;
		this.name = name;
	}
	
	public static SexEnum getSexEnum(int id) {
		for(SexEnum sex:SexEnum.values()) {
			if(sex.getCode() ==id) {
				return sex;
			}
		}
		return null;
	}
}
