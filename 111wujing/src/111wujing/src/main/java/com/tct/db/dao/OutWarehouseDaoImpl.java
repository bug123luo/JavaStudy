package com.tct.db.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.CancelRecipientsGunReqMessage;
import com.tct.codec.protocol.pojo.OutWarehouseReqMessage;
import com.tct.codec.protocol.pojo.OutWarehouseResMessage;
import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.mapper.MessageRecordsCustomMapper;
import com.tct.db.mapper.WarehouseRecordsCustomMapper;
import com.tct.db.po.AppGunCustom;
import com.tct.db.po.AppGunCustomQueryVo;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunCustomQueryVo;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.db.po.WarehouseRecords;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.db.po.WarehouseRecordsQueryVo;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

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
public class OutWarehouseDaoImpl implements OutWarehouseDao{
	
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
		appGunCustom.setGunId(oWReqMsg.getMessageBody().getGunId());
		appGunCustom.setAllotState(Integer.valueOf(StringConstant.GUN_ALLOTED_STATE));
		appGunCustomQueryVo.setAppGunCustom(appGunCustom);
		
		GunCustomQueryVo gunCustomQueryVo = new GunCustomQueryVo();
		GunCustom gunCustom = new GunCustom();
		gunCustom.setGunMac(oWReqMsg.getMessageBody().getGunMac());
		gunCustomQueryVo.setGunCustom(gunCustom);
		
		AppGunCustom appGunCustomTemp = appGunDao.selectAllColumn(appGunCustomQueryVo);
		GunCustom gunCustomTemp = gunDao.selectAllColumn(gunCustomQueryVo);
		WarehouseRecords warehouseRecords = new WarehouseRecords();
		warehouseRecords.setState(Integer.valueOf(StringConstant.OUT_WAREHOUSE_ING_STATE));
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
			warehouseRecords.setState(Integer.valueOf(StringConstant.OUT_WAREHOUSE_ING_STATE));
		}else if (oWRMsg.getMessageBody().getState().equals(StringConstant.SUCCESS_OLD_STATE)) {
			warehouseRecords.setState(Integer.valueOf(StringConstant.OUT_WAREHOUSE_ING_STATE));
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
		//warehouseRecordsCustom.setState(Integer.valueOf(1));
		warehouseRecordsQueryVo.setWarehouseRecordsCustom(warehouseRecordsCustom);
		log.info("出库根据GunId和state状态查询中的GunId为: "+oWReqMsg.getMessageBody().getGunId());
		return wrcDao.selectByGunIdAndState(warehouseRecordsQueryVo);
	}
	

	public int updateSelectiveByGunId(CancelRecipientsGunReqMessage cRecReqMsg) {
		
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		
		warehouseRecords.setGunId(cRecReqMsg.getMessageBody().getGunId());
		warehouseRecords.setState(Integer.valueOf(StringConstant.IN_WAREHOUSE_STATE));
		warehouseRecords.setCancelTime(StringUtil.getDate(cRecReqMsg.getMessageBody().getCancelTime()));
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunId(warehouseRecords);
		return i;
	}
	
	public int updateSelectiveByGunIdAndOutState(MessageRecordsCustom mRecCustom) {
		
		WarehouseRecords warehouseRecords= new WarehouseRecords();
		JSONObject jsontemp = JSONObject.parseObject(mRecCustom.getMessage());
		warehouseRecords.setGunId(jsontemp.getString("gunId"));
		warehouseRecords.setState(Integer.valueOf(StringConstant.IN_WAREHOUSE_STATE));
		warehouseRecords.setCancelTime(new Date());
		
		int i = 0;
		i = wrcDao.updateSelectiveByGunIdAndOutState(warehouseRecords);
		return i;
	}
	
}
