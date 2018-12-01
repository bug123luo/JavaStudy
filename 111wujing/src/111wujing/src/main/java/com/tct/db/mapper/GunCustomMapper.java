package com.tct.db.mapper;

import com.tct.db.po.Gun;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunCustomQueryVo;

/**
 * 
 * @ClassName:  GunCustomMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月7日 上午12:41:42   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public interface GunCustomMapper {
	Gun selectAllColumn(GunCustomQueryVo gunCustomQueryVo);
	
	Gun selectAllColumnByGunId(GunCustomQueryVo gunCustomQueryVo);
	
	GunCustom selectAllColumnByGunImei(GunCustomQueryVo gunCustomQueryVo);
	
	int updateSelectiveByGunId(GunCustom gunCustom);
	
	int updateByGunId(GunCustom gunCustom);
	
	int updateByGunIdAndCount(GunCustom gunCustom);
}
