/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SearchGunReqMessageBody.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午7:50:05   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import java.util.ArrayList;

import lombok.Data;

/**   
 * @ClassName:  SearchGunReqMessageBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午7:50:05   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class StartStopSearchGunReqMessageBody {
	private String command;//1 停止 0重启
	private ArrayList<GunInfo> gunList;//枪支mac列表
	private String authCode;//授权码
	
	@Data
	class GunInfo{
		private String gunMac;//枪支MAC
	}
}
