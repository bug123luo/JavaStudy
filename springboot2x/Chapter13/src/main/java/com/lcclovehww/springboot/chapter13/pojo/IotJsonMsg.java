/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  IotJsonMsg.java   
 * @Package com.lcclovehww.springboot.chapter13.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月5日 下午4:16:34   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.pojo;

import lombok.Data;

/**   
 * @ClassName:  IotJsonMsg   
 * @Description:物联网MQTT协议报文中的应用数据包类
 * @author: 泰源云景
 * @date:   2019年3月5日 下午4:16:34   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class IotJsonMsg {
	private String version;//版本号
	private String msgType;//消息类型
	private String msgSendTime;//消息发送时间
	private String msgSerial;//消息序列号
	private BaseSation msgBody;
}
