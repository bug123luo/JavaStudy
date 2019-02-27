/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserVo.java   
 * @Package com.lcclovehww.springboot.chapter11.vo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月27日 上午10:18:06   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter11.vo;

import com.lcclovehww.springboot.chapter11.enumeration.SexEnum;

import lombok.Data;

/**   
 * @ClassName:  UserVo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月27日 上午10:18:06   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class UserVo {

	private Long id;
	private String userName;
	private int sexCode;
	private String sexName;
	private String note;
	
	
}
