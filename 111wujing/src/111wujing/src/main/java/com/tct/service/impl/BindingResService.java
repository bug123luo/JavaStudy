/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BindingResService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月7日 上午10:31:55   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tct.codec.protocol.pojo.BindingResMessage;
import com.tct.db.dao.BindingGunDao;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  BindingResService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月7日 上午10:31:55   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service("bindingResService")
@Scope("prototype")
public class BindingResService implements TemplateService {

	@Autowired
	BindingGunDao bindingGunDao;
	
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		BindingResMessage bResMsg = (BindingResMessage)msg;
				
		bindingGunDao.updateGunState(bResMsg);
	}

}
