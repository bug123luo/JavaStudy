/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MessageRecordsDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月17日 下午6:26:01   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import com.tct.codec.protocol.pojo.CancelInWarehouseReqMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunReqMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunResMessage;
import com.tct.codec.protocol.pojo.GetBulletNumberReqMessage;
import com.tct.codec.protocol.pojo.InWarehouseReqMessage;
import com.tct.db.po.MessageRecordsCustom;

/**   
 * @ClassName:  MessageRecordsDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月17日 下午6:26:01   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface MessageRecordsDao {

	public int insertSelective(CancelRecipientsGunReqMessage cRecGunReqMsg);
	
	public MessageRecordsCustom selectBySerlNum(CancelRecipientsGunResMessage cRecGunResMsg);
	
	public MessageRecordsCustom selectBySerlNum(String serialNumber);
	
	public int insertSelective(InWarehouseReqMessage inWhReqMsg);
	
	public int insertSelective(CancelInWarehouseReqMessage msg);
	
	public int insertSelective(GetBulletNumberReqMessage msg);
}
