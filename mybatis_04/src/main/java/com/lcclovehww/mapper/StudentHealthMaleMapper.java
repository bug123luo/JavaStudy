/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  StudentMapper.java   
 * @Package com.lcclovehww.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年9月29日 上午11:19:33   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.mapper;

import java.util.List;

import com.lcclovehww.pojo.Student;
import com.lcclovehww.pojo.StudentHealthMale;

/**   
 * @ClassName:  StudentMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年9月29日 上午11:19:33   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface StudentHealthMaleMapper {
	List<StudentHealthMale> findStudentHealthMaleByStuId(Long id);
}
