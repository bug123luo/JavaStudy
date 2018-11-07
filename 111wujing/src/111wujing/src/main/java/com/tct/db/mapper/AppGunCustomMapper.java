package com.tct.db.mapper;

import com.tct.db.po.AppGunCustom;
import com.tct.db.po.AppGunCustomQueryVo;

/**
 * 
 * @ClassName:  AppGunCustomMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月7日 上午12:29:08   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public interface AppGunCustomMapper {
	AppGunCustom selectAllColumn(AppGunCustomQueryVo appGunCustomQueryVo);
	
	int insertSelective(AppGunCustom appGunCustom);
	
	int updateSelectiveByAppId(AppGunCustom appGunCustom);
	
	int updateSelectiveByAppIdAndState(AppGunCustom appGunCustom);
}
