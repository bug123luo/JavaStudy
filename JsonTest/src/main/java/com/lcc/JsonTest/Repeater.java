/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Repeater.java   
 * @Package com.lcclovehww.springboot.chapter13.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月5日 下午4:46:37   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.JsonTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lcc.JsonTest.DeviceMsg;
import lombok.Data;

/**   
 * @ClassName:  Repeater   
 * @Description:基站信息 
 * @author: 泰源云景
 * @date:   2019年3月5日 下午4:46:37   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
public class Repeater implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1135412958072052103L;
	private String id;//中继or采集器编号
	private List<DeviceMsg> devices = new ArrayList<DeviceMsg>();
}
