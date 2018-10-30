/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RecipientsGunReqMessageBody.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午5:08:24   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import lombok.Data;

/**   
 * @ClassName:  RecipientsGunReqMessageBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午5:08:24   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class OutWarehouseReqMessageBody{
	private String reserve;//预留字段
	private String username;//领用人员名字
	private String userId;//领用人员id
	private String gunId;//枪号
	private String gunMac;//枪支设备蓝牙号
	private String begintime;//开始领用时间
	private String returnTime;//归还截止时间
}
