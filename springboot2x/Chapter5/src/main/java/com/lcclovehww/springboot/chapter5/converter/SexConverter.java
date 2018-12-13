/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SexConverter.java   
 * @Package com.lcclovehww.springboot.chapter5.converter   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午10:09:17   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.converter;

import javax.persistence.AttributeConverter;

import com.lcclovehww.springboot.chapter5.enumeration.SexEnum;

/**   
 * @ClassName:  SexConverter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午10:09:17   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {

	/**   
	 * <p>Title: convertToDatabaseColumn</p>   
	 * <p>Description: </p>   
	 * @param sex
	 * @return   
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)   
	 */
	@Override
	public Integer convertToDatabaseColumn(SexEnum sex) {
		return sex.getId();
	}

	/**   
	 * <p>Title: convertToEntityAttribute</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)   
	 */
	@Override
	public SexEnum convertToEntityAttribute(Integer id) {
		return SexEnum.getEnumById(id);
	}

}
