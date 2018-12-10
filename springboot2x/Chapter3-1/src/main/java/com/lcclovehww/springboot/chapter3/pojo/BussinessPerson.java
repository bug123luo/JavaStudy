/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BussinessPerson.java   
 * @Package com.lcclovehww.springboot.chapter3.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月10日 下午2:44:42   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter3.pojo;

import static org.hamcrest.CoreMatchers.nullValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lcclovehww.springboot.chapter3.pojo.definition.Animal;
import com.lcclovehww.springboot.chapter3.pojo.definition.Person;

/**   
 * @ClassName:  BussinessPerson   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月10日 下午2:44:42   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
public class BussinessPerson implements Person {

	/**   
	 * <p>Title: service</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.springboot.chapter3.pojo.definition.Person#service()   
	 */
	

	private Animal animal = null;
	
	@Override
	public void service() {
		this.animal.use();
	}

	/**   
	 * <p>Title: setAnimal</p>   
	 * <p>Description: </p>   
	 * @param animal   
	 * @see com.lcclovehww.springboot.chapter3.pojo.definition.Person#setAnimal(com.lcclovehww.springboot.chapter3.pojo.definition.Animal)   
	 */
	@Override
	@Autowired
	@Qualifier("dog")
	public void setAnimal(Animal animal) {
		System.out.println("延迟依赖注入");
		this.animal = animal;
	}

}
