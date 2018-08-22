/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.dao;

import dudu.service.dao.bean.AccountBean;

public class AccountDao_old extends DuduDaoSupport {
	

	public AccountDao_old() {
		
	}
	
	public void save(AccountBean accountBean) throws DaoException {
		super.save(accountBean);
	}
	
	public void updatePassword(long userId, String password)
		throws DaoException {
		
		String hql = String.format(
			"UPDATE AccountBean ab SET ab.password=\'%s\', ab.salt='', ab.chgTime=%d " + 
			"WHERE ab.userId=%d",
			password,
			System.currentTimeMillis()/1000,
			userId);
		
		super.update(hql);
	}
	
	public void updatePassword(String phoneNumber, String password)
		throws DaoException {
		
		String hql = String.format(
			"UPDATE AccountBean ab SET ab.password=\'%s\', ab.salt='', ab.chgTime=%d " + 
			"WHERE ab.phone=\'%s\'",
			password,
			System.currentTimeMillis()/1000,
			phoneNumber);
		
		super.update(hql);
	}
	
	public boolean isPhoneNumberPresent(String phoneNumber)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBean ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		AccountBean accountBean = (AccountBean)super.uniqueResult(hql);
		if (accountBean != null) {
			return true;
		}
		
		return false;
	}
	
	public AccountBean get(String phoneNumber, String password)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBean ab WHERE ab.phone=\'%s\' and ab.password=\'%s\'",
			phoneNumber,
			password);
		
		return (AccountBean)super.uniqueResult(hql);
	}
	
	public AccountBean get(String phoneNumber)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBean ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		return (AccountBean)super.uniqueResult(hql);
	}
	
	public Long getUserId(String phoneNumber) throws DaoException {
		
		String hql = String.format(
			"FROM AccountBean ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		AccountBean bean = (AccountBean)super.uniqueResult(hql);
		if (bean != null) {
			return bean.getUserId();
		} else {
			return null;
		}
		
	}
	
	public String getUserPhone(Long userId) throws DaoException {
		
		String hql = String.format(
				"FROM AccountBean ab WHERE ab.userId=%d ",
				userId);
		
		AccountBean bean = (AccountBean)super.uniqueResult(hql);
		if (bean != null) {
			return bean.getPhone();
		} else {
			return null;
		}
		
	}
	
}
