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
package dudu.service.common;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dudu.service.dao.DaoException;
import dudu.service.dao.bean.GuardAreaBean;
import dudu.service.dao.bean.MessageBean;
import dudu.service.dao.bean.SOSBean;
import dudu.service.dao.bean.SensorConfigurationBean;
import dudu.service.dao.bean.SensorMessageBean;
import dudu.service.dto.Page;
import dudu.service.dto.SensorAccountData;

public interface SensorService {
	
	/*Status Interface*/
	public void setOnline(String sensorId, boolean isOnline) throws DaoException, CacheException;
	
	public Boolean isOnline(String sensorId)
		throws BusinessException, DaoException, CacheException;
	
	// 设置设备定位状态
	public void setPosition(String sensorId, boolean isPosition) throws DaoException, CacheException;
	
	// 判断设备定位状态
	public Boolean isPosition(String sensorId) 
		throws BusinessException, DaoException, CacheException;
	
	public void setSessionToken(String sensorId, String sessionToken) throws CacheException;
	
	public String getSessionToken(String sensorId) throws CacheException;
	
	public void invalidSessionToken(String sensorId) throws CacheException;
	
	/*Location Interface*/
	public void saveLocation(dudu.service.dao.bean.LocationBean locBean)
		throws BusinessException, DaoException;
	
	public void setLocation(dudu.service.dao.bean.LocationBean locBean)
		throws BusinessException;
	
	public dudu.service.dao.bean.LocationBean getLocation(String sensorId)
		throws BusinessException;
	
	public List<dudu.service.dao.bean.LocationBean> getTrack(
		String sensorId,
		Date startTime,
		Date endTime) 
		throws BusinessException, DaoException;
	
	public List<GuardAreaBean> getGuardAreaList(String sensorId)
		throws BusinessException, DaoException;
	
	public List<GuardAreaBean> getGuardAreaList(List<String> sensorIds)
		throws BusinessException, DaoException;
	
	public Long addGuardArea(
		String sensorId,
		Float longitude,
		Float latitude, 
		Integer radius,
		String tilte,
		String type,
		String startCron,
		String endCron) throws BusinessException, DaoException;
	
	public Long updateGuardArea(
		String guardId,
		String sensorId,
		Float longitude,
		Float latitude, 
		Integer radius,
		String tilte,
		String type,
		String startCron,
		String endCron) throws BusinessException, DaoException;
	
	public GuardAreaBean removeGuardArea(String sensorId, Long guardAreaId)
		throws BusinessException, DaoException;
	
	public void enableGps(String sensorId, Boolean enable) 
		throws BusinessException, DaoException;
	
	// 保存告警信息
	public void saveSOS(dudu.service.dao.bean.SOSBean sosBean) throws DaoException;
	
	// 获取告警信息
	public List<SOSBean> getSOSList(String sensorId, Date startTime, Date endTime)
		throws BusinessException, DaoException;
	
	public List<SOSBean> getSOSListByType(String sensorId, Date startTime, Date endTime, int type)
		throws BusinessException, DaoException;
	// 批量获取告警信息
	public List<SOSBean> getSOSList(List<String> sensorIds, Date startTime, Date endTime)
		throws BusinessException, DaoException;
	
	// 根据类型获取告警信息
	public List<MessageBean> getSOSMessages(long userId, List<String> sensorIds, int from, int to, List<String> types)
		throws BusinessException, DaoException;
	
	// 保存配置信息
	public void saveSensorConfiguration(SensorConfigurationBean scBean) throws BusinessException, DaoException;
	
	// 获取配置信息
	public SensorConfigurationBean getSensorConfiguration(String sensorId) throws BusinessException, DaoException;
	
	// 获取围栏真实Id
	public String getGuardAreaId(String sensorId, int trueId) throws BusinessException, DaoException;

	// 根据id获取围栏信息
	public GuardAreaBean getGuardArea(Long guId) throws BusinessException, DaoException;

	// 修改imsi和iccid信息
	public void updateImsiAndIccid(String sensorId, String imsi, String iccid) throws BusinessException, DaoException;
	
	// 修改围栏真实编号
	public void updateGuardAreaTrueId(long guardId, int trueId) throws BusinessException, DaoException;
	
	public long getEveryDayOnlineCount() throws BusinessException, DaoException, ParseException;
	
	public long getEveryDayActivateCount() throws BusinessException, DaoException, ParseException;
	
	// update guard in out status
	public void updateGuardInOutStatus(long guardId, int inOut, long inOutTime) throws BusinessException, DaoException;
	
	public void updateChatSwitch(String sensorId, int cSwitch) throws BusinessException, DaoException;
	
	// sensor message 
	public String saveSensorMessage(String sensorId, dudu.service.core.MessageBean pushMsg, String serialNumber) throws BusinessException, DaoException;
	public void updateSensorMessageSended(String sensorId, String serialNumber) throws BusinessException, DaoException;
	public SensorMessageBean getSendSensorMessage(String sensorId) throws BusinessException, DaoException;
	public SensorMessageBean getReSendSensorMessage(String sensorId, String serialNumber) throws BusinessException, DaoException;
	public void updateDown(String sensorId, String serialNumber, boolean flag) throws DaoException;
	public SensorMessageBean getDownSensorMessage(String sensorId) throws BusinessException, DaoException;
	
	// space status
	public void setSpaceStatus(String sensorId, boolean isFull) throws CacheException;
	public Boolean isSpaceFull(String sensorId) throws CacheException;
	
	// 获取58报文最后下发时间
	public void setRecordRespLastTime(String sensorId) throws CacheException;
	public void setRecordRespLastTimeCompeleted(String sensorId) throws CacheException;
	public Long getRecordRespLastTime(String sensorId) throws CacheException;
	
	/**
	 * 获取所有设备IMEI号，由逗号分隔,eg. 12323123,534234,323123,......
	 * @return
	 */
	public SensorAccountData getAllSensorId(Page page) throws BusinessException, DaoException;

	public Long saveTeaGuardArea(
			String sensorId,
			Float longitude,
			Float latitude,
			Integer radius,
			String tilte,
			String type,
			String startCron,
			String endCron) throws BusinessException, DaoException;

	public List<GuardAreaBean> getTeaGuardAreaList(String sensorId)
			throws BusinessException, DaoException;

	public String getTeaGuardAreaId(String sensorId, int trueId) throws BusinessException, DaoException;
}
