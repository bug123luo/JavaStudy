/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  TctWarningMsg.java   
 * @Package com.tct.rabbitmq.router.entity.third   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月29日 上午9:47:15   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.rabbitmq.router.entity.third;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**   
 * @ClassName:  TctWarningMsg   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月29日 上午9:47:15   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@Data
public class TctWarningMsg extends TctBaseMsg{
	@Getter
	@Setter
	private TctWarnigMsgBody msgBody;
}
