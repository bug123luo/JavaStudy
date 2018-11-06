/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AuthCodeDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月6日 上午10:13:21   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tct.db.mapper.AppMapperCustomer;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  AuthCodeDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月6日 上午10:13:21   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component
public class AuthCodeDao {
	
	@Autowired
	AppMapperCustomer appDao;
	
	public AppCustom selectAppAllColumn(AppCustomQueryVo appCustomQueryVo) {
		AppCustom appCustom =null;
		try {
			appCustom = appDao.selectAppAllColumn(appCustomQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appCustom;
	}
}
