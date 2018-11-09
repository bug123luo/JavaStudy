/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  WatchHeartReqMessageBody.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午7:31:05   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import java.util.ArrayList;


import lombok.Data;

/**   
 * @ClassName:  WatchHeartReqMessageBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午7:31:05   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class WatchHeartReqMessageBody {
	private String state;//腕表的出入库状态?
	private String deviceType;//设备类型
	private String lo;//经度
	private String la;//纬度
	private String areaCode;//小区代码
	private String appBatteryPower;//随行设备电量
	private ArrayList<Guninfo> gunList;//枪支列表
	private String exceptionCode;//异常时间码
	private String authCode;//授权码
	
	@Data
	public class Guninfo{
		private String gunId;//枪号
		private String realTimeState;//随行状态
		private String gunDeviceBatteryPower;//定位模组电量
	}
	
}
