/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Squirrel.java   
 * @Package com.lcclovehww.springboot.chapter3.other.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月10日 下午5:25:48   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter3.other.pojo;

import com.lcclovehww.springboot.chapter3.pojo.definition.Animal;

/**   
 * @ClassName:  Squirrel   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月10日 下午5:25:48   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Squirrel implements Animal {

	/**   
	 * <p>Title: use</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.springboot.chapter3.pojo.definition.Animal#use()   
	 */
	@Override
	public void use() {
		System.out.println("松鼠可以采集松果");
	}

}
