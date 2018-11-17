/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HeartbeatDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月17日 下午6:23:28   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import com.tct.codec.protocol.pojo.DeviceHeartReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartReqMessage;

/**   
 * @ClassName:  HeartbeatDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月17日 下午6:23:28   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface HeartbeatDao {

	public int insertAppHeartbeatSelective(WatchHeartReqMessage wHRMsg);
	
	public int intsertDeviceSelective(DeviceHeartReqMessage dhrm);
}
