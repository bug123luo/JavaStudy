/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SexEnumTypeHandler.java   
 * @Package com.lcclovehww.typeHandler   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月21日 下午4:36:55   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.lcclovehww.enums.Sex;

/**   
 * @ClassName:  SexEnumTypeHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月21日 下午4:36:55   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class SexEnumTypeHandler implements TypeHandler<Sex> {

	/**   
	 * <p>Title: setParameter</p>   
	 * <p>Description: </p>   
	 * @param ps
	 * @param i
	 * @param parameter
	 * @param jdbcType
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)   
	 */
	public void setParameter(PreparedStatement ps, int i, Sex sex, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, sex.getId());
	}

	/**   
	 * <p>Title: getResult</p>   
	 * <p>Description: </p>   
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)   
	 */
	public Sex getResult(ResultSet rs, String columnName) throws SQLException {
		int id =  rs.getInt(columnName);
		return Sex.getSex(id);
	}

	/**   
	 * <p>Title: getResult</p>   
	 * <p>Description: </p>   
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)   
	 */
	public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
		int id = rs.getInt(columnIndex);
		return Sex.getSex(id);
	}

	/**   
	 * <p>Title: getResult</p>   
	 * <p>Description: </p>   
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException   
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)   
	 */
	public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
		int id= cs.getInt(columnIndex);
		return Sex.getSex(id);
	}

}
