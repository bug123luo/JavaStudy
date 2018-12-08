/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AppGunDaoImpl.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月7日 下午5:05:54   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.po.AppGunCustom;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  AppGunDaoImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月7日 下午5:05:54   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component
public class AppGunDaoImpl implements AppGunDao {

	@Autowired
	AppGunCustomMapper agcDao;
	
	/**   
	 * <p>Title: updateSelectiveByGunIdAndAllocState</p>   
	 * <p>Description: </p>   
	 * @param appGunCustom
	 * @return   
	 * @see com.tct.db.dao.AppGunDao#updateSelectiveByGunIdAndAllocState(com.tct.db.po.AppGunCustom)   
	 */
	@Override
	public int updateSelectiveByGunIdAndUnallocState(AppGunCustom appGunCustom) {
		
		return agcDao.updateSelectiveByGunIdAndUnallocState(appGunCustom);
	}

	/**   
	 * <p>Title: updateSelectiveByGunIdAndAllocState</p>   
	 * <p>Description: </p>   
	 * @param appGunCustom
	 * @return   
	 * @see com.tct.db.dao.AppGunDao#updateSelectiveByGunIdAndAllocState(com.tct.db.po.AppGunCustom)   
	 */
	@Override
	public int updateSelectiveByGunIdAndAllocState(AppGunCustom appGunCustom) {
		
		return agcDao.updateSelectiveByGunIdAndAllocState(appGunCustom);
	}

	/**   
	 * <p>Title: updateSelectiveByGunIdAndInitState</p>   
	 * <p>Description: </p>   
	 * @param appGunCustom
	 * @return   
	 * @see com.tct.db.dao.AppGunDao#updateSelectiveByGunIdAndInitState(com.tct.db.po.AppGunCustom)   
	 */
	@Override
	public int updateSelectiveByGunIdAndInitState(AppGunCustom appGunCustom) {
		// TODO Auto-generated method stub
		return agcDao.updateSelectiveByGunIdAndInitState(appGunCustom);
	}

}
