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

import java.util.Random;

import dudu.service.dao.bean.AccountBean;
import dudu.service.dao.bean.AccountBeanEx;

public class AccountExDao extends DuduDaoSupport {
	

	public AccountExDao() {
		
	}
	
	public void save(AccountBean accountBean) throws DaoException {
		AccountBeanEx accountBeanEx = new AccountBeanEx();
		accountBeanEx.setUid(accountBean.getCrtTime());
		accountBeanEx.setName(createUsername(accountBean.getPhone()));
		accountBeanEx.setPhone(accountBean.getPhone());
		accountBeanEx.setPasswd(accountBean.getPassword());
		accountBeanEx.setEmail("");
		accountBeanEx.setCode(0);
		accountBeanEx.setIsPhone(0);
		accountBeanEx.setIsEmail(0);
		accountBeanEx.setBalance(0.0f);
		accountBeanEx.setEnbalance(0.0f);
		accountBeanEx.setPoints(0);
		accountBeanEx.setAblepoints(0);
		accountBeanEx.setRegTime(accountBean.getCrtTime());
		accountBeanEx.setRegIp("");
		accountBeanEx.setLastTime(accountBean.getChgTime());
		accountBeanEx.setLastLoginIp("");
		accountBeanEx.setStatus(0);
		accountBeanEx.setSex(0);
		accountBeanEx.setLogo("");
		accountBeanEx.setIsFxs(0);
		accountBeanEx.setSalt("");
	
		super.save(accountBeanEx);
	}
	
	
	public void updatePassword(long userId, String password)
		throws DaoException {
		
		String hql = String.format(
			"UPDATE AccountBeanEx ab SET ab.passwd=\'%s\', ab.salt='', ab.lastTime=%d " + 
			"WHERE ab.id=%d",
			password,
			System.currentTimeMillis()/1000,
			userId);
		
		super.update(hql);
	}
	
	public void updatePassword(String phoneNumber, String password)
		throws DaoException {
		
		String hql = String.format(
			"UPDATE AccountBeanEx ab SET ab.passwd=\'%s\', ab.salt='', ab.lastTime=%d " + 
			"WHERE ab.phone=\'%s\'",
			password,
			System.currentTimeMillis()/1000,
			phoneNumber);
		
		super.update(hql);
	}
	
	public boolean isPhoneNumberPresent(String phoneNumber)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBeanEx ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		AccountBeanEx accountBean = (AccountBeanEx)super.uniqueResult(hql);
		if (accountBean != null) {
			return true;
		}
		
		return false;
	}
	
	public AccountBeanEx get(String phoneNumber, String password)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBeanEx ab WHERE ab.phone=\'%s\' and ab.passwd=\'%s\'",
			phoneNumber,
			password);
		
		return (AccountBeanEx)super.uniqueResult(hql);
	}
	
	public AccountBeanEx get(String phoneNumber)
		throws DaoException {
		
		String hql = String.format(
			"FROM AccountBeanEx ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		return (AccountBeanEx)super.uniqueResult(hql);
	}
	
	public Long getUserId(String phoneNumber) throws DaoException {
		
		String hql = String.format(
			"FROM AccountBeanEx ab WHERE ab.phone=\'%s\'",
			phoneNumber);
		
		AccountBeanEx bean = (AccountBeanEx)super.uniqueResult(hql);
		if (bean != null) {
			return bean.getId();
		} else {
			return null;
		}
		
	}
	
	public String getUserPhone(Long userId) throws DaoException {
		
		String hql = String.format(
				"FROM AccountBeanEx ab WHERE ab.id=%d ",
				userId);
		
		AccountBeanEx bean = (AccountBeanEx)super.uniqueResult(hql);
		if (bean != null) {
			return bean.getPhone();
		} else {
			return null;
		}
		
	}
	
	private String createUsername(String mobile) {
		return  String.format("u%s%s%s", mobile.substring(0, 3), getRandomStr(), mobile.substring(7));
	}
	
	private static String getRandomStr() {
		final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		
		Random random = new Random();
		for (int i=1; i<=4; i++) {
			int j = random.nextInt(ABC.length()-1);
			sb.append(ABC.charAt(j));
		}
		
		return sb.toString();
	}
	
	
	public void updateAvator(String icon, Long userId)
			throws DaoException {
			
			String hql = String.format(
				"UPDATE AccountBeanEx ab SET ab.logo=\'%s\' WHERE ab.id=\'%d\'",icon, userId);
			
			super.update(hql);
		}
	
	public String getAvator(Long userId)
			throws DaoException {
			
			String hql = String.format(
				"FROM AccountBeanEx ab WHERE ab.id=%d ",userId);
			
			AccountBeanEx bean = (AccountBeanEx)super.uniqueResult(hql);
			if (bean != null) {
				return bean.getLogo();
			} else {
				return null;
			}
		}
}
