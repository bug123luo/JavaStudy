/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  PersistenceUser.java   
 * @Package com.lcclovehww.springboot.chapter5.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午10:02:41   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.pojo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.exception.spi.SQLExceptionConverter;

import com.lcclovehww.springboot.chapter5.converter.SexConverter;
import com.lcclovehww.springboot.chapter5.enumeration.SexEnum;

import lombok.Data;

/**   
 * @ClassName:  PersistenceUser   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午10:02:41   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Data
@Entity(name="user")
@Table(name="t_user")
public class PersistenceUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id = null;
	
	@Column(name="user_name")
	private String userName=null;
	
	@Convert(converter = SexConverter.class)
	private SexEnum sex = null;
	
	private String note = null;
}
