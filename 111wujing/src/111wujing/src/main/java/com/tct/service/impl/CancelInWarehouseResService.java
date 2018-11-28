/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  CancelInWarehouseResService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月13日 下午6:52:14   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tct.codec.protocol.pojo.CancelInWarehouseResMessage;
import com.tct.db.dao.InWarehouseDao;
import com.tct.db.dao.InWarehouseDaoImpl;
import com.tct.db.dao.MessageRecordsDao;
import com.tct.db.dao.MessageRecordsDaoImpl;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.util.StringConstant;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  CancelInWarehouseResService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月13日 下午6:52:14   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("cancelInWarehouseResService")
@Scope("prototype")
@Slf4j
public class CancelInWarehouseResService implements TemplateService {

	@Autowired
	InWarehouseDao iwhDao;
	
	@Autowired
	MessageRecordsDao mrDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		CancelInWarehouseResMessage ciwhr = (CancelInWarehouseResMessage)msg;

		MessageRecordsCustom mrsCustom=mrDao.selectBySerlNum(ciwhr.getSerialNumber());
		
		String state=ciwhr.getMessageBody().getState();

		if(state.equals(StringConstant.SUCCESS_NEW_STATE)) {
			//iwhDao.updateSelectiveByGunIdAndMaxTime(mrsCustom.getGunId());
			iwhDao.updateSelectiveByGunIdAndRollbackState(mrsCustom.getGunId());
		}else {
			//iwhDao.updateSelectiveByGunIdAndMaxTime(mrsCustom.getGunId());
			iwhDao.updateSelectiveByGunIdAndInState(mrsCustom.getGunId());
		}

	}

}
