/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MybatisUser.java   
 * @Package com.lcclovehww.springboot.chapter5.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午11:36:27   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.pojo;

import org.apache.ibatis.type.Alias;

import com.lcclovehww.springboot.chapter5.enumeration.SexEnum;

import lombok.Data;

/**   
 * @ClassName:  MybatisUser   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午11:36:27   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
@Alias(value="mybatisUser")
public class MybatisUser {

	private Long id = null;
	private String userName=null;
	private SexEnum sex = null;
	private String note = null;
}
