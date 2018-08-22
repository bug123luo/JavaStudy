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

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dudu.service.dto.Page;

public class DuduDaoSupport extends HibernateDaoSupport {

	private static Logger logger=LoggerFactory.getLogger(DuduDaoSupport.class);

	public DuduDaoSupport() {

	}

	public <T> void save(T entity) throws DaoException {

		try {
			getHibernateTemplate().save(entity);

		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	public <T> void saveOrUpdate(T entity) throws DaoException {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	public <T> void update(T entity) throws DaoException {
		try {
			getHibernateTemplate().update(entity);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	public void update(String hql) throws DaoException {

		logger.debug(".update(hql=\"{}\")", hql);

		try {
			getHibernateTemplate().bulkUpdate(hql);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String hql) throws DaoException {

		logger.debug(".query(hql=\"{}\")", hql);
		Session session = null;
		List<T> list = null;

		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			list = query.list();
			if (list == null) {
				logger.debug("NO Record.");
			} else {
				logger.debug("Record size: {}.", list.size());
			}

		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public Object uniqueResult(String hql) throws DaoException {

		logger.debug(".uniqueResult(hql=\"{}\")", hql);

		Session session = null;
		Object obj = null;

		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			String queryString = hql;
			Query query = session.createQuery(queryString);
			obj = query.uniqueResult();
			if (obj == null) {
				logger.debug("NO Record.");
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return obj;
	}

	public long count(final String hql) throws DaoException {

		logger.debug(".count(hql=\"{}\")", hql);

		DaoException selectException = null;
		Session session = null;
		ScrollableResults srs = null;
		long count = 0;

		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			String queryString = hql;
			Query query = session.createQuery(queryString);
			srs = query.scroll();
			if (srs != null && srs.next()) {
				count = srs.getLong(0).longValue();
				srs.close();
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			selectException = new DaoException(e.getMessage(), e.getCause());
		} finally {
			if (srs != null) {
				srs.close();
			}

			if (session != null) {
				session.close();
			}
		}

		if (selectException != null) {
			throw selectException;
		}

		logger.debug("cout = {}", count);

		return count;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> c, Serializable id) throws DaoException {
		Session session = null;
		T bean = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			bean = (T)session.get(c, id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return bean;
	}
	
	// 为所有子类添加分页查询、按某列排序、分组等功能
		@SuppressWarnings("unchecked")
		public <T> void find(String queryString, List<T> items, Page page) throws DaoException {
			Session session = null;
			try {
				// 分组
				if (page.getGroupByColumn() == null
						|| page.getGroupByColumn().trim().equals(""))
					;
				else
					queryString += " group by e." + page.getGroupByColumn();
				// 排序
				if (page.getOrderByColumn() == null
						|| page.getOrderByColumn().trim().equals(""))
					;
				else
					queryString += " order by e." + page.getOrderByColumn()
							+ (page.isAsc() ? " asc" : " desc");
				// 获取并设置总的记录数
				int count = getHibernateTemplate().find(queryString).size();
				page.setTotalItemNumber(count);
				
				session = getHibernateTemplate().getSessionFactory().openSession();
				List<T> copyItems = (List<T>) (
						session	.createQuery(queryString)
						.setFirstResult(
								(page.getPageNumber() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				// 将查询得到的对象数组添加到我们传入的List对象中
				items.addAll(copyItems);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new DaoException(e.getMessage(), e.getCause());
			}finally{
				if (session != null) {
					session.close();
				}
			}
		}
}
