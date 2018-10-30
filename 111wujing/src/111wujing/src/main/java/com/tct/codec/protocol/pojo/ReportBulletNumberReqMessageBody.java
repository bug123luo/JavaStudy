/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ReportBulletNumberReqMessageBody.java   
 * @Package com.tct.codec.protocol.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午8:13:16   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.protocol.pojo;

import java.nio.channels.ScatteringByteChannel;

import org.apache.camel.processor.validation.PredicateValidationException;

import lombok.Data;

/**   
 * @ClassName:  ReportBulletNumberReqMessageBody   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午8:13:16   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class ReportBulletNumberReqMessageBody {
	private String gunId;//枪号
	private String bullet_number;//发生射弹数
	private String lo;//经度
	private String la;//纬度
	private String time;//时间
	private String authCode;//授权码
}
