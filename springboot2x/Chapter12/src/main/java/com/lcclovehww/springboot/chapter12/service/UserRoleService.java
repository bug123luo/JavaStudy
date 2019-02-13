/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserRoleService.java   
 * @Package com.lcclovehww.springboot.chapter12.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月22日 下午2:38:23   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter12.service;

import com.lcclovehww.springboot.chapter12.pojo.DatabaseUser;
import java.util.List;
import com.lcclovehww.springboot.chapter12.pojo.DatabaseRole;

/**   
 * @ClassName:  UserRoleService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月22日 下午2:38:23   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface UserRoleService {

	public DatabaseUser getUserByName(String userName);

	public List<DatabaseRole> findRolesByUserName(String userName);
}
