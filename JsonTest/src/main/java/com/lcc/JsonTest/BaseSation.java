/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BaseSation.java   
 * @Package com.lcclovehww.springboot.chapter13.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月5日 下午4:44:16   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.JsonTest;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  BaseSation   
 * @Description:基站类   
 * @author: 泰源云景
 * @date:   2019年3月5日 下午4:44:16   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class BaseSation implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = -5749728338059085664L;
	private String serial;//基站编号
	private Repeater repeater;
}
