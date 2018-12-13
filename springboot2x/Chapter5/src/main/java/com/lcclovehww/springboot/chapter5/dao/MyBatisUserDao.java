/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyBatisUserDao.java   
 * @Package com.lcclovehww.springboot.chapter5.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 下午12:03:46   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.dao;

import org.springframework.stereotype.Repository;
import com.lcclovehww.springboot.chapter5.pojo.MybatisUser;

/**   
 * @ClassName:  MyBatisUserDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 下午12:03:46   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Repository
public interface MyBatisUserDao {
	public MybatisUser getUser(Long id);
}
