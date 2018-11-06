package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.mapper.WarehouseRecordsCustomMapper;
import com.tct.db.po.AppGunCustom;
import com.tct.db.po.AppGunCustomQueryVo;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunCustomQueryVo;
import com.tct.db.po.WarehouseRecords;

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
	public int insertWarehouseRecords(AppGunCustomQueryVo appGunCustomQueryVo,GunCustomQueryVo gunCustomQueryVo) {
		AppGunCustom appGunCustom = appGunDao.selectAllColumn(appGunCustomQueryVo);
		GunCustom gunCustom = gunDao.selectAllColumn(gunCustomQueryVo);
		WarehouseRecords warehouseRecords = new WarehouseRecords();
		warehouseRecords.setState(Integer.valueOf(1));
		warehouseRecords.setAppId(appGunCustom.getAppId());
		warehouseRecords.setGunId(gunCustom.getGunId());
		int i=wrcDao.insertSelective(warehouseRecords);
		return i;
	}
}
