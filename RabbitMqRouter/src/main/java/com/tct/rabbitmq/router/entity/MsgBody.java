/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MsgBody.java   
 * @Package com.lcc.JsonTest   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月6日 上午9:41:34   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.rabbitmq.router.entity;


import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**   
 * @ClassName:  MsgBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月6日 上午9:41:34   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class MsgBody {
	private int base;//基站编号
	private int repeater;//中继or采集器编号
	private String tag;//标签名//人员标签 id
	private String status;//状态 on:off
}
