/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SexTypeHandler.java   
 * @Package com.lcclovehww.springboot.chapter5.typeHandler   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午11:39:28   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.lcclovehww.springboot.chapter5.enumeration.SexEnum;

/**   
 * @ClassName:  SexTypeHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午11:39:28   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value=SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum>{

	/**   
	 * <p>Title: setNonNullParameter</p>   
	 * <p>Description: </p>   
	 * @param ps
	 * @param i
	 * @param parameter
	 * @param jdbcType
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)   
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int idx, SexEnum sex, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(idx, sex.getId());
	}

	/**   
	 * <p>Title: getNullableResult</p>   
	 * <p>Description: </p>   
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)   
	 */
	@Override
	public SexEnum getNullableResult(ResultSet rs, String col) throws SQLException {
		int sex = rs.getInt(col);
		
		if(sex != 1 && sex !=2) {
			return null;
		}
		
		return SexEnum.getEnumById(sex);
	}

	/**   
	 * <p>Title: getNullableResult</p>   
	 * <p>Description: </p>   
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)   
	 */
	@Override
	public SexEnum getNullableResult(ResultSet rs, int idx) throws SQLException {
		int sex = rs.getInt(idx);
		if(sex!=1 && sex!=2) {
			return null;
		}
		return SexEnum.getEnumById(sex);
	}

	/**   
	 * <p>Title: getNullableResult</p>   
	 * <p>Description: </p>   
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)   
	 */
	@Override
	public SexEnum getNullableResult(CallableStatement cs, int idx) throws SQLException {
		int sex = cs.getInt(idx);
		if(sex!=1 && sex!=2) {
			return null;
		}
		return SexEnum.getEnumById(sex);
	}

	
}
