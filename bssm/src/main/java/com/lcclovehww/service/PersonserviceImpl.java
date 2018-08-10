/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  PersonserviceImpl.java   
 * @Package com.lcclovehww.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月10日 下午5:41:34   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PrinterStateReasons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcclovehww.mapper.PersonCustomMapper;
import com.lcclovehww.mapper.PersonMapper;
import com.lcclovehww.po.Person;
import com.lcclovehww.po.PersonQueryVo;

/**   
 * @ClassName:  PersonserviceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月10日 下午5:41:34   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@Service
public class PersonserviceImpl implements PersonService {

	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	PersonCustomMapper personCustomMapper;
	/**   
	 * <p>Title: getallPerson</p>   
	 * <p>Description: </p>   
	 * @param map
	 * @return   
	 * @see com.lcclovehww.service.PersonService#getallPerson(java.util.Map)   
	 */
	public List<Person> getallPerson(Map map) {

		Map mp=map;
		try {
			return personCustomMapper.selectByPersonQueryVo(mp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**   
	 * <p>Title: getallPersonByPname</p>   
	 * <p>Description: </p>   
	 * @param map
	 * @return   
	 * @see com.lcclovehww.service.PersonService#getallPersonByPname(java.util.Map)   
	 */
	public List<Person> getallPersonByPname(Map map) {
		Map mp=map;
		try {
			return personCustomMapper.selectByPersonQueryVo(mp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**   
	 * <p>Title: getPersonById</p>   
	 * <p>Description: </p>   
	 * @param pid
	 * @return   
	 * @see com.lcclovehww.service.PersonService#getPersonById(int)   
	 */
	public Person getPersonById(int pid) {
		return personMapper.selectByPrimaryKey(pid);
	}

	/**   
	 * <p>Title: delperson</p>   
	 * <p>Description: </p>   
	 * @param pid   
	 * @see com.lcclovehww.service.PersonService#delperson(int)   
	 */
	public void delperson(int pid) {
		personMapper.deleteByPrimaryKey(pid);
	}

	/**   
	 * <p>Title: addperson</p>   
	 * <p>Description: </p>   
	 * @param person   
	 * @see com.lcclovehww.service.PersonService#addperson(com.lcclovehww.po.Person)   
	 */
	public void addperson(Person person) {
		personMapper.insertSelective(person);
	}

	/**   
	 * <p>Title: updateperson</p>   
	 * <p>Description: </p>   
	 * @param person   
	 * @see com.lcclovehww.service.PersonService#updateperson(com.lcclovehww.po.Person)   
	 */
	public void updateperson(Person person) {
		personMapper.updateByPrimaryKeySelective(person);
	}

}
