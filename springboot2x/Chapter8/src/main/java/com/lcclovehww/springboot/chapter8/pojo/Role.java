/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Role.java   
 * @Package com.lcclovehww.springboot.chapter8.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月2日 下午2:45:03   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.pojo;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**   
 * @ClassName:  Role   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月2日 下午2:45:03   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Document
@Data
public class Role implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = -5281303009828752760L;

	private Long id;
	@Field("role_name")
	private String roleName = null;
	private String note = null;
	
}
