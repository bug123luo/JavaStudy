/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  InWarehouseDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月12日 上午9:07:16   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.InWarehouseReqMessage;
import com.tct.codec.protocol.pojo.InWarehouseResMessage;
import com.tct.db.mapper.WarehouseRecordsCustomMapper;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.db.po.WarehouseRecords;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.db.po.WarehouseRecordsQueryVo;
import com.tct.util.StringConstant;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  InWarehouseDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月12日 上午9:07:16   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component
public class InWarehouseDaoImpl implements InWarehouseDao{

	@Autowired
	WarehouseRecordsCustomMapper wrcDao;
	
	public int updateSelectiveByGunIdAndMaxTime(String gunId) {
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		warehouseRecords.setGunId(gunId);
		warehouseRecords.setState(Integer.valueOf(StringConstant.OUT_WAREHOUSE_STATE));
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunIdAndMaxTime(warehouseRecords);
		return i;
	}
	
	public int updateSelectiveByGunIdAndRollbackState(String gunId) {
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		//JSONObject jsontemp = JSONObject.parseObject(mRecCustom.getMessage());
		warehouseRecords.setGunId(gunId);
		warehouseRecords.setState(Integer.valueOf(StringConstant.OUT_WAREHOUSE_STATE));
		//warehouseRecords.setCancelTime(new Date());
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunIdAndRollbackState(warehouseRecords);
		return i;
	}
	
	public int updateSelectiveByGunIdAndIngState(String gunId) {
		
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		//JSONObject jsontemp = JSONObject.parseObject(mRecCustom.getMessage());
		warehouseRecords.setGunId(gunId);
		warehouseRecords.setState(Integer.valueOf(StringConstant.IN_WAREHOUSE_STATE));
		//warehouseRecords.setCancelTime(new Date());
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunIdAndInState(warehouseRecords);
		return i;
	}
		
	public WarehouseRecordsCustom selectByGunIdAndIngState(InWarehouseReqMessage inWhReqMsg) {
		
		WarehouseRecordsQueryVo warehouseRecordsQueryVo = new WarehouseRecordsQueryVo();
		WarehouseRecordsCustom warehouseRecordsCustom = new WarehouseRecordsCustom();
		warehouseRecordsCustom.setGunId(inWhReqMsg.getMessageBody().getGunId());
		warehouseRecordsCustom.setState(Integer.valueOf(StringConstant.IN_WAREHOUSE_ING_STATE));
		warehouseRecordsQueryVo.setWarehouseRecordsCustom(warehouseRecordsCustom);
		
		return wrcDao.selectByGunIdAndInState(warehouseRecordsQueryVo);
		
	}
	
}
