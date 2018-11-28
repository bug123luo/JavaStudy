package com.tct.db.mapper;

import com.tct.db.po.WarehouseRecords;
import com.tct.db.po.WarehouseRecordsCustom;
import com.tct.db.po.WarehouseRecordsQueryVo;

/**
 * 
 * @ClassName:  WarehouseRecordsCustomMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月7日 上午12:45:13   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public interface WarehouseRecordsCustomMapper {
	
	int insertSelective(WarehouseRecords warehouseRecords);
	
	int updateSelectiveByGunId(WarehouseRecords warehouseRecords);
	
	int updateSelectiveByGunIdAndOutState(WarehouseRecords warehouseRecords);
	
	WarehouseRecordsCustom selectByGunIdAndState(WarehouseRecordsQueryVo warehouseRecordsQueryVo);
	
	int updateSelectiveByGunIdAndInState(WarehouseRecords warehouseRecords);
	
	int updateSelectiveByGunIdAndIngState(WarehouseRecords warehouseRecords);
	
	int updateSelectiveByGunIdAndRollbackState(WarehouseRecords warehouseRecords);
	
	int updateSelectiveByGunIdAndMaxTime(WarehouseRecords warehouseRecords);
	
	WarehouseRecordsCustom selectByGunIdAndInState(WarehouseRecordsQueryVo warehouseRecordsQueryVo);
}