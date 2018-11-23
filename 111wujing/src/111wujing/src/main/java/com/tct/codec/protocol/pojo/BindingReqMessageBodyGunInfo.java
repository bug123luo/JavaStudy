/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BindingReqMessageBodyGunInfo.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月23日 下午4:51:51   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import lombok.Data;

/**   
 * @ClassName:  BindingReqMessageBodyGunInfo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月23日 下午4:51:51   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class BindingReqMessageBodyGunInfo {
	private String gunType;//枪支类型：长枪 短枪
	private String gunId;//枪号
	private String gunModel;//枪行
	private String gunMac;//枪支设备蓝牙编号
}
