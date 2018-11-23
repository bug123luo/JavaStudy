/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  TemplateMessage.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午3:58:01   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  TemplateMessage   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午3:58:01   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class TemplateMessage implements Serializable {

	/**   
	 * @Fields serialVersionUID : 消息需要在网络中传递与处理，需要对消息包进行序列化处理  
	 */ 
	private static final long serialVersionUID = -7506921994436483752L;
	
	private String uniqueIdentification;//唯一标识符
	private String formatVersion;//协议版本号
	private Integer deviceType;//设备类型
	private String serialNumber;//交易流水号
	private String messageType;//报文类型
	private String sendTime;//发报时间
	private String sessionToken;//标识当前回话的session
}
