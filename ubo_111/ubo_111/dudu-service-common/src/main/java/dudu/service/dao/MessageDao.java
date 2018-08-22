package dudu.service.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import dudu.service.dao.bean.MessageBean;

public class MessageDao extends DuduDaoSupport {
	
	//private static final Logger LOG=LoggerFactory.getLogger(MessageDao.class);
	
	private static final String TYPE_RECORD_SOS    = "record#sos";     //告警录音(子)
	private static final String TYPE_GUARD         = "guard";          //进出围栏
	private static final String TYPE_LOWBAT        = "lowbat";         //低电告警

	public MessageDao() {

	}

	public MessageBean get(String msgId) throws DaoException {
		return super.get(MessageBean.class, msgId);
	}

	public void save(MessageBean message) throws DaoException {
		super.save(message);
	}

	public void updateReadStatus(String msgId, Date date) throws DaoException {
		
		/*
		String hql = String
			.format(
				"UPDATE MessageBean mb SET mb.read=true,mb.chgTime=%d WHERE mb.msgId=\'%s\'",
				date.getTime() / 1000, msgId);
		*/
		
		// 不修改时间，只修改已读
		String hql = String
			.format(
				"UPDATE MessageBean mb SET mb.read=true WHERE mb.msgId=\'%s\'", msgId);

		super.update(hql);
		
	}

	public List<MessageBean> get(final long userId, final int from, final int to)
		throws DaoException {
		
		// 在HQL中不能直接使用 LIMIT 进行分页 
		/*
		String hql = String
			.format(
				"FROM MessageBean mb WHERE mb.msgId like \'%09d%%\' ORDER BY mb.msgId DESC LIMIT %d,%d",
				userId, from, to);
		List<MessageBean> list = super.query(hql);
		*/
		
		return this.getHibernateTemplate().execute(new HibernateCallback<List<MessageBean>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<MessageBean> doInHibernate(Session session)
				throws HibernateException {
				String hql = String
					.format(
						"FROM MessageBean mb WHERE mb.msgId like \'%09d%%\' ORDER BY mb.msgId DESC",
						userId);
				final Query query = session.createQuery(hql);
			    query.setFirstResult(from);    
			    query.setMaxResults(to);
			    return query.list();
			}
		});

	}
	
	public List<MessageBean> getUserMessages(long userId, int from, int to, List<String> types)
			throws DaoException {
		List<MessageBean> list = new ArrayList<MessageBean>();
		for (String type: types) {
			List<MessageBean> messages = get(userId, from, to, type);
			if (messages != null) {
				
				list.addAll(messages);
			}
		}
		return list;
	}
	
	public List<MessageBean> get(final long userId, final int from, final int to, final String type)
			throws DaoException {
			return this.getHibernateTemplate().execute(new HibernateCallback<List<MessageBean>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<MessageBean> doInHibernate(Session session)
					throws HibernateException {
					
					String hql = "";
					
					if (TYPE_GUARD.equals(type) || TYPE_LOWBAT.equals(type) || TYPE_RECORD_SOS.equals(type)) {
						
						long before24hours = System.currentTimeMillis()/1000 - 24*60*60;
						
						hql = String
							.format(
								"FROM MessageBean mb WHERE mb.msgId LIKE \'%09d%%\' AND mb.type = \'%s\' AND mb.chgTime > %d ORDER BY mb.msgId DESC",
								userId,
								type,
								before24hours);
						
					} else {
						hql = String
							.format(
								"FROM MessageBean mb WHERE mb.msgId LIKE \'%09d%%\' AND mb.type = \'%s\' ORDER BY mb.msgId DESC",
								userId, type);
					}

					final Query query = session.createQuery(hql);
				    query.setFirstResult(from);
				    query.setMaxResults(to);
				    return query.list();
				}
			});
	}
	
	public List<MessageBean> get(List<String> sensorIds, int from, int to, String type) throws DaoException {
		List<MessageBean> list = new ArrayList<MessageBean>();
		for (String sensorId: sensorIds) {
			List<MessageBean> messages = get(sensorId, from, to, type);
			if (messages != null && !messages.isEmpty()) {
				list.addAll(messages);
			}
		}
		return list;
	}
	
	public List<MessageBean> getSensorMessages(long userId, List<String> sensorIds, int from, int to, List<String> types) throws DaoException {
		List<MessageBean> list = new ArrayList<MessageBean>();
		for (String sensorId: sensorIds) {
			for (String type: types) {
				List<MessageBean> messages = get(userId, sensorId, from, to, type);
				if (messages != null && !messages.isEmpty()) {
					list.addAll(messages);
				}
			}
		}
		return list;
	}

	public List<MessageBean> get(final String sensorId, final int from, final int to, final String type)
			throws DaoException {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<MessageBean>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<MessageBean> doInHibernate(Session session)
					throws HibernateException {
				String hql = String
						.format(
								"FROM MessageBean mb WHERE mb.msgId LIKE \'%s%%\' AND mb.type = \'%s\' ORDER BY mb.msgId DESC",
								sensorId, type);
				
				final Query query = session.createQuery(hql);
				query.setFirstResult(from);    
				query.setMaxResults(to);
				return query.list();
			}
		});
	}
	
	public List<MessageBean> get(final long userId, final String sensorId, final int from, final int to, final String type)
			throws DaoException {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<MessageBean>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<MessageBean> doInHibernate(Session session)
					throws HibernateException {
				String hql = String
						.format(
								"FROM MessageBean mb WHERE mb.msgId like \'%09d%%\' AND mb.body LIKE \'%%%s%%\' AND mb.type = \'%s\' ORDER BY mb.chgTime DESC",
								userId, sensorId, type);
				
				final Query query = session.createQuery(hql);
				query.setFirstResult(from);    
				query.setMaxResults(to);
				return query.list();
			}
		});
	}
	

	public List<MessageBean> get(long userId)
			throws DaoException {

			String hql = String
				.format(
					"FROM MessageBean mb WHERE mb.msgId like \'%09d%%\' AND mb.read=false ORDER BY mb.msgId DESC",
					userId);

			List<MessageBean> list = super.query(hql);

			return list;
		}
}
