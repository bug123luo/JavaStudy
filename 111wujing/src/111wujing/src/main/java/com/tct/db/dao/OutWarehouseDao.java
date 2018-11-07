package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tct.codec.protocol.pojo.OutWarehouseReqMessage;
import com.tct.codec.protocol.pojo.OutWarehouseResMessage;
import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.mapper.WarehouseRecordsCustomMapper;
import com.tct.db.po.AppGunCustom;
import com.tct.db.po.AppGunCustomQueryVo;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunCustomQueryVo;
import com.tct.db.po.WarehouseRecords;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.db.po.WarehouseRecordsQueryVo;
import com.tct.util.StringConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  OutWarehouseDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月6日 下午11:33:01   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Slf4j
@Component
public class OutWarehouseDao {
	
	@Autowired
	AppGunCustomMapper appGunDao;
	
	@Autowired
	GunCustomMapper gunDao;
	
	@Autowired
	WarehouseRecordsCustomMapper wrcDao;
	
	@Transactional
	public int insertWarehouseRecords(OutWarehouseReqMessage oWReqMsg) {
		AppGunCustomQueryVo appGunCustomQueryVo = new AppGunCustomQueryVo();
		AppGunCustom appGunCustom = new AppGunCustom();
		appGunCustom.setGunId(Integer.valueOf(oWReqMsg.getMessageBody().getGunId()));
		appGunCustom.setAllotState(Integer.valueOf(1));
		appGunCustomQueryVo.setAppGunCustom(appGunCustom);
		
		GunCustomQueryVo gunCustomQueryVo = new GunCustomQueryVo();
		GunCustom gunCustom = new GunCustom();
		gunCustom.setBluetoothMac(oWReqMsg.getMessageBody().getGunMac());
		gunCustomQueryVo.setGunCustom(gunCustom);
		
		AppGunCustom appGunCustomTemp = appGunDao.selectAllColumn(appGunCustomQueryVo);
		GunCustom gunCustomTemp = gunDao.selectAllColumn(gunCustomQueryVo);
		WarehouseRecords warehouseRecords = new WarehouseRecords();
		warehouseRecords.setState(Integer.valueOf(1));
		warehouseRecords.setAppId(appGunCustomTemp.getAppId());
		warehouseRecords.setGunId(gunCustomTemp.getGunId());
		
		int i = 0;
		i=wrcDao.insertSelective(warehouseRecords);
		return i;
	}
			
	public int updateSelectiveByGunId(OutWarehouseResMessage oWRMsg) {
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		
		warehouseRecords.setGunId(oWRMsg.getMessageBody().getGunId());
		if(oWRMsg.getMessageBody().getState().equals(StringConstant.SUCCESS_NEW_STATE)) {
			warehouseRecords.setState(Integer.valueOf(1));
		}else if (oWRMsg.getMessageBody().getState().equals(StringConstant.SUCCESS_OLD_STATE)) {
			warehouseRecords.setState(Integer.valueOf(1));
		}else {
			
		}
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunId(warehouseRecords);
		return i;
	}
	
	public WarehouseRecordsCustom selectByGunIdAndState(OutWarehouseReqMessage oWReqMsg) {
		WarehouseRecordsQueryVo warehouseRecordsQueryVo = new WarehouseRecordsQueryVo();
		WarehouseRecordsCustom warehouseRecordsCustom = new WarehouseRecordsCustom();
		warehouseRecordsCustom.setGunId(oWReqMsg.getMessageBody().getGunId());
		warehouseRecordsCustom.setState(Integer.valueOf(1));
		warehouseRecordsQueryVo.setWarehouseRecordsCustom(warehouseRecordsCustom);
		
		return wrcDao.selectByGunIdAndState(warehouseRecordsQueryVo);
	}
	

}
