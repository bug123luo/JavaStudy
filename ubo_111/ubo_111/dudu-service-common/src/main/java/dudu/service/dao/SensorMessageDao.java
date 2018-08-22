package dudu.service.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.dao.bean.SensorMessageBean;

public class SensorMessageDao extends DuduDaoSupport {
	
	private static Logger logger = LoggerFactory.getLogger(SensorMessageDao.class);
	
	private static final int CACHE_COUNT = 10;

	public SensorMessageDao() {

	}

	public SensorMessageBean get(String msgId) throws DaoException {
		return super.get(SensorMessageBean.class, msgId);
	}

	public void save(SensorMessageBean message) throws DaoException {
		String sensorId = message.getMsgId().substring(0, 15);  // 新加 
		Long count = getCacheSendMessageCount(sensorId);        // 新加
		
		logger.debug(String.format("SensorMessageBean#getCacheSendMessageCount sensorId=%s, Count = %d" , sensorId, count));
		
		if (count > CACHE_COUNT) {                              // 新加 缓存大于 10条替换旧的
			deleteLastCacheMessage(sensorId);                   // 新加
		}
		
		super.save(message);
	}

	public SensorMessageBean getDownMessage(String sensorId)
		throws DaoException {

		SensorMessageBean rsmb = null;

		/*
		String hql = String.format(
			"FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.down=false AND smb.expire=false"
				+ " ORDER BY smb.msgId ASC",
			sensorId);
		*/
		
		//  优先下发失败的语音
		rsmb = getDownFailMessage(sensorId);
		
		logger.debug("getDownFailMessage(sensorId={},rsmb={})" , sensorId, rsmb);
		
		if (rsmb != null) {
			return rsmb;
		}
		
		// 下发未下发的语音
		String hql = String.format(
			"FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.down=false AND smb.expire=false"
				+ " ORDER BY smb.serialNumber ASC",
				sensorId);

		List<SensorMessageBean> list = super.query(hql);

		if (list != null && !list.isEmpty()) {
			rsmb = list.get(0);
		}
		
		if (rsmb != null) {
			updateDown(sensorId, rsmb.getSerialNumber(), true);
		}
	
		return rsmb;
	}
	
	// 获取下发失败的语音数据重发
	public SensorMessageBean getDownMessage2(String sensorId)
		throws DaoException {

		SensorMessageBean rsmb = null;

		// 使用序列号排序
		String hql = String.format(
			"FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.down=true AND smb.expire=false"
				+ " ORDER BY smb.serialNumber ASC",
			sensorId);

		List<SensorMessageBean> list = super.query(hql);

		if (list != null && !list.isEmpty()) {
			for (int i=0; i<list.size(); i++) {
				SensorMessageBean message = list.get(i);
				if (Math.abs(System.currentTimeMillis()/1000 - message.getChgTime()) > 60) {
					rsmb = message;
					break;
				}
			}
		}
		
		if (rsmb != null) {
			updateDown(sensorId, rsmb.getSerialNumber(), true);
		}
		
		return rsmb;
	}
	
	
	// 获取下发失败的语音数据重发
	public SensorMessageBean getDownFailMessage(String sensorId)
		throws DaoException {

		SensorMessageBean rsmb = null;

		// 使用序列号排序
		String hql = String.format(
			"FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.down=true AND smb.expire=false"
				+ " ORDER BY smb.serialNumber ASC",
			sensorId);

		List<SensorMessageBean> list = super.query(hql);

		if (list != null && !list.isEmpty()) {
			rsmb = list.get(0);
		}

		return rsmb;
	}
	
	public Long getCacheSendMessageCount(String sensorId) throws DaoException {
		
		String hql = String.format(
			"SELECT COUNT(*) FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.expire=false",
			sensorId); // AND smb.down=false
		
		Long count = (Long) super.uniqueResult(hql);
		
		return count;
	}
	
	public void deleteLastCacheMessage(String sensorId) throws DaoException {
		
		String hql = String.format(
			"UPDATE SensorMessageBean smb SET smb.expire=true WHERE smb.msgId=\'%s\'",
			getMinMsgId(sensorId));
		
		super.update(hql);
	}
	
	public String getMinMsgId(String sensorId) throws DaoException {
		String msgId = "";
		String hql = String.format(
			"SELECT min(smb.msgId) FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.send=false AND smb.expire=false",
			sensorId); // AND smb.down=false
		
		Object object = super.uniqueResult(hql);
		
		if (object != null) {
			msgId = (String) object;
		}
		
		logger.debug(String.format("SensorMessageBean#getMinMsgId msgId = %s" ,msgId));
		
		return msgId;
	}
	
	public SensorMessageBean getSensorMessage(String sensorId, String serialNumber)
		throws DaoException {

		String hql = String.format(
			"FROM SensorMessageBean smb WHERE smb.msgId LIKE \'%s%%\' AND smb.serialNumber=\'%s\'",
			sensorId,
			serialNumber);

		return (SensorMessageBean) super.uniqueResult(hql);

	}
	
	public void updateSendedStatus(String sensorId, String serialNumber) throws DaoException {

		String hql = String.format(
			"UPDATE SensorMessageBean smb SET smb.send=true, smb.chgTime=%d WHERE smb.msgId LIKE \'%s%%\' AND smb.serialNumber=\'%s\'",
			System.currentTimeMillis() / 1000, 
			sensorId,
			serialNumber);

		super.update(hql);
	}
	
	public void updateDown(String sensorId, String serialNumber, boolean flag) throws DaoException {
		
		String bFlag = flag ? "true" : "false";
		
		String hql = String.format(
			"UPDATE SensorMessageBean smb SET smb.down=%s, smb.chgTime=%d WHERE smb.msgId LIKE \'%s%%\' AND smb.serialNumber=\'%s\'",
			bFlag,
			System.currentTimeMillis() / 1000,
			sensorId,
			serialNumber);
		
		super.update(hql);
	}
	
}
