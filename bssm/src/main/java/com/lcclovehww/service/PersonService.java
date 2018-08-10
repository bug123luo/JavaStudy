/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  IPserson01.java   
 * @Package com.lcclovehww.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月10日 下午4:52:54   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lcclovehww.po.Person;


/**   
 * @ClassName:  IPserson01   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月10日 下午4:52:54   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public interface PersonService {
    public List<Person> getallPerson(Map map);

    public List<Person> getallPersonByPname(Map map);

    public Person getPersonById(int pid);

    public void delperson(int pid);

    public void addperson(Person person);

    public void updateperson(Person person);

}
