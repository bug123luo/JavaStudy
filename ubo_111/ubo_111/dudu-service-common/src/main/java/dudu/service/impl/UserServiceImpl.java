package dudu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.BusinessException;
import dudu.service.common.UserService;
import dudu.service.dao.DaoException;
import dudu.service.dao.MessageDao;
import dudu.service.dao.UserOnlineDao;
import dudu.service.dao.bean.MessageBean;
import dudu.service.dao.bean.UserOnlineBean;
import net.sf.json.JSONObject;

public class UserServiceImpl implements UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private MessageDao msgDao;
	private UserOnlineDao onlineDao;
	/*yyyyMMddHHmmssSSS*/
	private SimpleDateFormat sdf;
	
	public UserServiceImpl() {
		
	}

	public MessageDao getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(MessageDao msgDao) {
		this.msgDao = msgDao;
	}

	public void setOnlineDao(UserOnlineDao onlineDao) {
		this.onlineDao = onlineDao;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	@Override
	public Boolean isUserOnline(Long userId) throws BusinessException,
		DaoException {
		
		return false;
	}
	
	@Override
	public String saveMessage(Long userId, dudu.service.core.MessageBean pushMsg)
		throws DaoException {
		
		Date time = new Date();
		String timestamp = sdf.format(time);
		String msgId = String.format("%09d%s", userId, timestamp);
		MessageBean daoMsgBean = new MessageBean();
		daoMsgBean.setMsgId(msgId);
		pushMsg.setMsgId(msgId);
		daoMsgBean.setBody(JSONObject.fromObject(pushMsg).toString());
		daoMsgBean.setRead(false);
		daoMsgBean.setType(pushMsg.getType());
		daoMsgBean.setChgTime(time.getTime()/1000);
		msgDao.save(daoMsgBean);
		
		return msgId;
	}

	@Override
	public void saveMessage(MessageBean daoMsgBean) throws DaoException {
		msgDao.save(daoMsgBean);
	}

	@Override
	public void updateMessage(String msgId, Boolean isRead, Date chgTime)
		throws DaoException {
		
		if (isRead) {
			msgDao.updateReadStatus(msgId, chgTime);
		}
	}

	@Override
	public List<MessageBean> getMessage(Long userId, int from, int to)
		throws DaoException {
		
		if (from < 0) {
			from = 0;
		}
		
		return msgDao.get(userId, from, to);
	}

	@Override
	public List<MessageBean> getMessage(Long userId) throws DaoException {
		return msgDao.get(userId);
	}

	@Override
	public void setOnline(Long usreId, boolean isOnline)
		throws BusinessException, DaoException {
		logger.debug(".setOnline({},{})", usreId, isOnline);	
		
		UserOnlineBean onlineBean = onlineDao.get(usreId);
		if (onlineBean != null) {
			onlineDao.update(usreId, isOnline);
		} else {
			onlineBean = new UserOnlineBean();
			onlineBean.setUserId(usreId);
			onlineBean.setOnline(isOnline);
			Date date = Calendar.getInstance().getTime();
			onlineBean.setCrtTime(date.getTime()/1000);
			onlineBean.setChgTime(date.getTime()/1000);
			onlineDao.save(onlineBean);
		}
	}

	@Override
	public long getEveryDayOnlineCount()
		throws BusinessException, DaoException, ParseException {
		return onlineDao.getOnlineCount();
	}

	@Override
	public long getEveryDayActivateCount()
		throws BusinessException, DaoException, ParseException {
		return onlineDao.getActivateCount();
	}

	@Override
	public String saveMessage(String sensorId, dudu.service.core.MessageBean pushMsg) throws DaoException {
		Date time = new Date();
		String timestamp = sdf.format(time);
		String msgId = String.format("%s%s", sensorId, timestamp);
		MessageBean daoMsgBean = new MessageBean();
		daoMsgBean.setMsgId(msgId);
		pushMsg.setMsgId(msgId);
		daoMsgBean.setBody(JSONObject.fromObject(pushMsg).toString());
		daoMsgBean.setRead(false);
		daoMsgBean.setType(pushMsg.getType());
		daoMsgBean.setChgTime(time.getTime()/1000);
		msgDao.save(daoMsgBean);
		
		return msgId;
	}
	
	@Override
	public String saveMessage(Long userId,
		dudu.service.core.MessageBean pushMsg, String subType)
			throws DaoException {
		
		Date time = new Date();
		String timestamp = sdf.format(time);
		String msgId = String.format("%09d%s", userId, timestamp);
		MessageBean daoMsgBean = new MessageBean();
		daoMsgBean.setMsgId(msgId);
		pushMsg.setMsgId(msgId);
		daoMsgBean.setBody(JSONObject.fromObject(pushMsg).toString());
		daoMsgBean.setRead(false);
		daoMsgBean.setType(pushMsg.getType()+"#"+subType);
		daoMsgBean.setChgTime(time.getTime()/1000);
		msgDao.save(daoMsgBean);
		
		return msgId;
	}
	
	@Override
	public List<MessageBean> getUserMessage(Long userId, int from, int to, List<String> types) throws DaoException {
		return msgDao.getUserMessages(userId, from, to, types);
	}

	@Override
	public void updateMessageBat(List<String> ids, Boolean isRead, Date chgTime) throws DaoException {
		if (isRead) {
			for (String id: ids) {
				msgDao.updateReadStatus(id, chgTime);
			}
		}
	}


	
}
