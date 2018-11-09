/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AppDynamicDataCustomMapper.java   
 * @Package com.tct.db.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月9日 上午10:33:21   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.mapper;

import com.tct.db.po.AppDynamicDataCustom;

/**   
 * @ClassName:  AppDynamicDataCustomMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月9日 上午10:33:21   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

public interface AppDynamicDataCustomMapper {
	int insertSelective(AppDynamicDataCustom appDynamicDataCustom);
}
