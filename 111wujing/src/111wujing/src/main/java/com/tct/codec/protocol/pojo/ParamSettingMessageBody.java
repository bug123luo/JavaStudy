/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ParamSettingReqMessageBody.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午8:23:50   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import javax.print.DocFlavor.STRING;

import lombok.Data;

/**   
 * @ClassName:  ParamSettingReqMessageBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午8:23:50   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class ParamSettingMessageBody {
	
	private String powerAlarmLevel;//电量报警级别
	private String transmittingPower;//发射功率
	private String broadcastInterval;//广播间隔
	private String connectionInterval;//连接间隔
	private String connectionTimeout;//连接超时
	private String softwareDeviceVersion;//软硬件版本
	private String heartbeat;//心跳间隔
	private String powerSampling;//电量采样间隔
	private String systemTime;//系统时间
	private String matchTime;//匹配最大时间
	private String positioningInterval;//定位间隔
	private String safeCode;//安全字
	private String authCode;//授权码
}
