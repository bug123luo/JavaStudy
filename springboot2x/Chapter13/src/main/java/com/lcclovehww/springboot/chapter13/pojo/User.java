/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  User.java   
 * @Package com.lcclovehww.springboot.chapter13.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午3:39:44   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.pojo;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  User   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午3:39:44   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class User implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1520548650439162582L;
	private Long id;
	private String userName = null;
	private String note = null;
	
	public User(Long id, String userName, String note) {
		this.id=id;
		this.userName=userName;
		this.note=note;
	}
}
