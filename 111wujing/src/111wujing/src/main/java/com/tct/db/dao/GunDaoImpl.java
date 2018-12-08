/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  GunDaoImpl.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月8日 下午4:11:30   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.po.Gun;
import com.tct.db.po.GunCustom;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  GunDaoImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月8日 下午4:11:30   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component("gunDao")
public class GunDaoImpl implements GunDao {
	
	@Autowired
	GunCustomMapper gunDao;

	/**   
	 * <p>Title: updateSelectiveByGunId</p>   
	 * <p>Description: </p>   
	 * @param gunId
	 * @return   
	 * @see com.tct.db.dao.GunDao#updateSelectiveByGunId(java.lang.String)   
	 */
	@Override
	public int updateSelectiveByGunId(GunCustom gunCustom) {
		return gunDao.updateSelectiveByGunId(gunCustom);
	}
	
	
}
