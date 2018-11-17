/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AuthCodeDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月17日 下午6:19:00   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import com.tct.codec.protocol.pojo.AuthorizationReqMessage;
import com.tct.codec.protocol.pojo.RegistReqMessage;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;

/**   
 * @ClassName:  AuthCodeDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月17日 下午6:19:00   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface AuthCodeDao {
	
	public AppCustom selectAppAllColumn(AppCustomQueryVo appCustomQueryVo);
	
	public AppCustom selectAppAllColumn(AuthorizationReqMessage arq);
	
	public AppCustom selectAppAllColumn(RegistReqMessage regReqMsg);
}
