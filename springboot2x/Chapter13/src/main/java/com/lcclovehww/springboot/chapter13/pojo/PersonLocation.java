/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  PersonLocation.java   
 * @Package com.lcclovehww.springboot.chapter13.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月14日 下午3:47:52   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.pojo;

import java.util.Date;
import lombok.Data;

/**   
 * @ClassName:  PersonLocation   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月14日 下午3:47:52   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class PersonLocation {

	private long id;
	
	private int baseStationId;
	
	private int deviceId;
	
	private Date reportTime;
	
	private String personId;
	
	private String status;
	
}
