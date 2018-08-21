/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyStringTypeHandler.java   
 * @Package com.lcclovehww.typeHandler   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月21日 下午2:54:30   
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
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

/**   
 * @ClassName:  MyStringTypeHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月21日 下午2:54:30   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyStringTypeHandler implements TypeHandler<String> {

	private Logger log= Logger.getLogger(MyStringTypeHandler.class);
	
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
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		log.info("使用我的TypeHandler");
		ps.setString(i, parameter);
		
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
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		log.info("使用我的TypeHandler,ResultSet 列名获取字符串");
		return rs.getString(columnName);
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
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		log.info("使用我的TypeHandler，ResultSet下标获取字符串");
		return rs.getString(columnIndex);
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
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		log.info("使用我的TypeHandler，CallableStatement 下标获取字符串");
		return null;
	}

}
