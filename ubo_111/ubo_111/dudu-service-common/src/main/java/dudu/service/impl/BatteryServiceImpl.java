package dudu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.BatteryService;
import dudu.service.common.BusinessException;
import dudu.service.common.CacheService;
import dudu.service.common.DuduUtils;
import dudu.service.dao.bean.LocationBean;
import dudu.service.dto.BatteryStatusBean;
import net.sf.json.JSONObject;

public class BatteryServiceImpl implements BatteryService {
	private static Logger logger = LoggerFactory.getLogger(BatteryServiceImpl.class);
	private CacheService cacheService;
	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public Boolean saveBatteryStatus(BatteryStatusBean batteryMessage) throws BusinessException {
		logger.debug(
				".saveBatteryStatus(data={})",
				JSONObject.fromObject(batteryMessage).toString());
			
			String key = "BATTERY-" + batteryMessage.getSensorId();
			logger.debug("Key: {}", key);
			try {
				cacheService.set(key, batteryMessage);
			} catch (Exception e) {
				logger.error(DuduUtils.getThrowableInfo(e));
				return false;
			}
		return true;
	}

	@Override
	public BatteryStatusBean loadBatteryStatus(String sensorId) throws BusinessException {
		logger.debug(".loadBatteryStatus(sensorId={})", sensorId);
		
		String key = "BATTERY-" + sensorId;
		try {
			if (cacheService.isKeyInCache(key)) {
				BatteryStatusBean bean = (BatteryStatusBean)cacheService.get(key);
				return bean;
			}
		} catch (Exception e) {
			logger.error(DuduUtils.getThrowableInfo(e));
		}
		
		return null;
	}

}
