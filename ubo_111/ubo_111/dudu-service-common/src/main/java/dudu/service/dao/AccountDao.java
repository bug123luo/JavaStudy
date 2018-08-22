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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import dudu.service.dao.bean.AccountBean;
import dudu.service.dao.bean.AccountBeanEx;
import dudu.service.dao.bean.MessageBean;

public class AccountDao extends DuduDaoSupport {
	

	public AccountDao() {
		
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
		
		accountBean.setUserId(accountBeanEx.getId());
		
		// 兼容旧商城保存账号到旧商城表
		// super.save(accountBean);
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
	
	public void updatePasswordById(long userId, String password,String phone)
		throws DaoException {
		
		String hql = String.format(
			"UPDATE AccountBeanEx ab SET ab.passwd=\'%s\',ab.phone=\'%s\', ab.salt='', ab.lastTime=%d " +
			"WHERE ab.id=%d",
			password,
			phone,
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

	public void updateIosToken(String phoneNumber, String iosToken)
			throws DaoException {

		String hql = String.format(
				"UPDATE AccountBeanEx ab SET ab.iosToken=\'%s\', ab.lastTime=%d " +
						"WHERE ab.phone=\'%s\'",
				iosToken,
				System.currentTimeMillis()/1000,
				phoneNumber);

		super.update(hql);
	}

	public AccountBeanEx getById(Long userId)
			throws DaoException {

		String hql = String.format(
				"FROM AccountBeanEx ab WHERE ab.id=\'%s\'",
				userId);

		return (AccountBeanEx)super.uniqueResult(hql);
	}
	public List<AccountBeanEx> getByUnionId(final String unionId)
			throws DaoException {

		/*String hql = String.format(
				"FROM AccountBeanEx ab WHERE ab.unionId=\'%s\'",
				unionId);
		List<Object> query = super.query(hql);
        @SuppressWarnings("unchecked")
		List<AccountBeanEx> acounts =(List<AccountBeanEx>)query.iterator();
        return acounts;*/

		/*String hql = String.format(
				"FROM AccountBeanEx ab WHERE ab.unionId=\'%s\'",
				unionId);
		Session session = null;
		List<AccountBeanEx> beans=new ArrayList<AccountBeanEx>();
		try {			
			session = getHibernateTemplate().getSessionFactory().openSession();
			ArrayList<AccountBeanEx> copyItems = (ArrayList<AccountBeanEx>) (
					session.createQuery(hql).list());
			// 将查询得到的对象数组添加到我们传入的List对象中
			beans.addAll(copyItems);			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}finally{
			if (session != null) {
				session.close();
			}
		}
        return beans;*/
		return this.getHibernateTemplate().execute(new HibernateCallback<List<AccountBeanEx>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<AccountBeanEx> doInHibernate(Session session)
				throws HibernateException {
				String hql = String.format(
						"FROM AccountBeanEx ab WHERE ab.unionId=\'%s\'",
						unionId);
				final Query query = session.createQuery(hql);
			   
			    return query.list();
			}
		});

	}

	public void saveWeixId(String unionid,String phoneNumber)
			throws DaoException {

		String hql = String.format(
				"UPDATE AccountBeanEx ab SET ab.unionId=\'%s\', ab.lastTime=%d " +
						"WHERE ab.phone=\'%s\'",
				unionid,
				System.currentTimeMillis()/1000,
				phoneNumber);

		super.update(hql);
	}
}
