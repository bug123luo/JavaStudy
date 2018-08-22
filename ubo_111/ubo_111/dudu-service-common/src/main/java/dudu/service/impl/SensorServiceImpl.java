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
package dudu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.BusinessErrorConst;
import dudu.service.common.BusinessException;
import dudu.service.common.CacheException;
import dudu.service.common.CacheService;
import dudu.service.common.DuduUtils;
import dudu.service.common.SensorService;
import dudu.service.dao.DaoException;
import dudu.service.dao.GuardAreaDao;
import dudu.service.dao.LocationDao;
import dudu.service.dao.MessageDao;
import dudu.service.dao.SOSDao;
import dudu.service.dao.SensorConfigurationDao;
import dudu.service.dao.SensorMessageDao;
import dudu.service.dao.SensorOnlineDao;
import dudu.service.dao.bean.GuardAreaBean;
import dudu.service.dao.bean.LocationBean;
import dudu.service.dao.bean.MessageBean;
import dudu.service.dao.bean.SOSBean;
import dudu.service.dao.bean.SensorConfigurationBean;
import dudu.service.dao.bean.SensorMessageBean;
import dudu.service.dao.bean.SensorOnlineBean;
import dudu.service.dto.Page;
import dudu.service.dto.SensorAccountData;
import net.sf.json.JSONObject;


public class SensorServiceImpl implements SensorService {

    private static Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    private LocationDao locationDao;
    private GuardAreaDao guardAreaDao;
    private SensorOnlineDao onlineDao;
    private SOSDao sosDao;
    private SensorConfigurationDao configurationDao;
    private MessageDao messageDao;
    private SensorMessageDao sensorMessageDao;
    private CacheService cacheService;
    private SimpleDateFormat sdf;

    public SensorServiceImpl() {
        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setGuardAreaDao(GuardAreaDao guardAreaDao) {
        this.guardAreaDao = guardAreaDao;
    }

    public void setOnlineDao(SensorOnlineDao onlineDao) {
        this.onlineDao = onlineDao;
    }

    public void setSosDao(SOSDao sosDao) {
        this.sosDao = sosDao;
    }

    public void setConfigurationDao(SensorConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public void setSensorMessageDao(SensorMessageDao sensorMessageDao) {
        this.sensorMessageDao = sensorMessageDao;
    }

    @Override
    public Boolean isOnline(String sensorId) throws BusinessException,
            DaoException, CacheException {

        logger.debug(".isOnline({})", sensorId);

        String key = "SENSOR-ONLINE-" + sensorId;
        Boolean isOnline = false;

        do {
            Object obj = cacheService.get(key);
            if (obj != null) {
                isOnline = ((Boolean) obj).booleanValue();
                break;
            }

            SensorOnlineBean onlineBean = onlineDao.get(sensorId);
            if (onlineBean != null) {
                isOnline = onlineBean.getOnline();
                cacheService.set(key, new Boolean(isOnline));
            }

        } while (false);

        logger.debug("{} : {}.", key, isOnline);
        if (isOnline != null && isOnline.booleanValue() == true) {
            return true;
        }

        return false;

    }

    @Override
    public void setOnline(String sensorId, boolean isOnline) throws DaoException, CacheException {

        logger.debug(".setOnline({},{})", sensorId, isOnline);

        SensorOnlineBean onlineBean = onlineDao.get(sensorId);
        if (onlineBean != null) {
            onlineDao.update(sensorId, isOnline);
            //如果为设备登出，则置其定位状态为未定位
            if (!isOnline) {
                this.setPosition(sensorId, false);
            }
        } else {
            onlineBean = new SensorOnlineBean();
            onlineBean.setSensorId(sensorId);
            onlineBean.setOnline(isOnline);
            onlineBean.setPosition(false);
            Date date = Calendar.getInstance().getTime();
            onlineBean.setCrtTime(date.getTime() / 1000);
            onlineBean.setChgTime(date.getTime() / 1000);
            onlineDao.save(onlineBean);
        }

        String key = "SENSOR-ONLINE-" + sensorId;
        cacheService.set(key, isOnline);

    }

    @Override
    public void setSessionToken(String sensorId, String sessionToken)
            throws CacheException {

        logger.debug(".setSessionToken({},{})", sensorId, sessionToken);

        String key = "SESSION-TOKEN-" + sensorId;
        try {
            cacheService.set(key, sessionToken);
        } catch (Exception e) {
            throw new CacheException(e);
        }

    }

    @Override
    public String getSessionToken(String sensorId) throws CacheException {

        logger.debug(".getSessionToken({})", sensorId);

        String key = "SESSION-TOKEN-" + sensorId;
        String sessionToken = null;
        try {
            sessionToken = (String) cacheService.get(key);
            logger.debug("{} session token: {}.", key, sessionToken);
        } catch (Exception e) {
            throw new CacheException(e);
        }

        return sessionToken;
    }

    @Override
    public void invalidSessionToken(String sensorId) throws CacheException {

        logger.debug(".invalidSessionToken({})", sensorId);

        String key = "SESSION-TOKEN-" + sensorId;
        try {
            if (cacheService.isKeyInCache(key)) {
                logger.debug("{} is in cache.", key);
                cacheService.remove(key);
            }
        } catch (Exception e) {
            throw new CacheException(e);
        }

    }

    @Override
    public void saveLocation(LocationBean locBean) throws BusinessException,
            DaoException {

        logger.debug(".saveLocation({})", JSONObject.fromObject(locBean).toString());
        locationDao.save(locBean);
        setLocation(locBean);
    }

    @Override
    public void setLocation(LocationBean locBean) throws BusinessException {

        logger.debug(
                ".setLocation(sensorId={})",
                JSONObject.fromObject(locBean).toString());

        String key = "LOC-" + locBean.getLocId().split("#")[0];
        logger.debug("Key: {}", key);
        try {
            cacheService.set(key, locBean);
        } catch (Exception e) {
            logger.error(DuduUtils.getThrowableInfo(e));
        }
    }

    @Override
    public LocationBean getLocation(String sensorId) throws BusinessException {

        logger.debug(".getLocation(sensorId={})", sensorId);

        String key = "LOC-" + sensorId;
        try {
            if (cacheService.isKeyInCache(key)) {
                LocationBean bean = (LocationBean) cacheService.get(key);
                return bean;
            }
        } catch (Exception e) {
            logger.error(DuduUtils.getThrowableInfo(e));
        }

        return null;
    }

    @Override
    public List<LocationBean> getTrack(
            String sensorId,
            Date startTime,
            Date endTime) throws BusinessException, DaoException {

        logger.debug(
                ".getTrack(sensorId={},start={},end={})",
                new Object[]{sensorId, startTime, endTime});

        return locationDao.get(sensorId, startTime, endTime);

    }

    @Override
    public List<GuardAreaBean> getGuardAreaList(String sensorId)
            throws BusinessException, DaoException {

        logger.debug(".getGuardAreaList(sensorId={})", sensorId);
        List<GuardAreaBean> list =
                guardAreaDao.get(sensorId, GuardAreaDao.Flag.ACTIVATED_FLAG);
        if (list != null) {
            logger.debug("GardArea List.size={}", list.size());
        }

        return list;
    }

    @Override
    public List<GuardAreaBean> getGuardAreaList(List<String> sensorIds)
            throws BusinessException, DaoException {

        logger.debug(".getGuardAreaList(sensorIds={})", sensorIds);
        List<GuardAreaBean> list =
                guardAreaDao.get(sensorIds, GuardAreaDao.Flag.ACTIVATED_FLAG);
        if (list != null) {
            logger.debug("GardArea List.size={}", list.size());
        }

        return list;
    }

    @Override
    public Long addGuardArea(
            String sensorId,
            Float longitude,
            Float latitude,
            Integer radius,
            String title,
            String type,
            String startCron,
            String endCron) throws BusinessException, DaoException {

        logger.debug(".addGuardArea(sensorId={},lo={},la={},r={})",
                new Object[]{sensorId, longitude, latitude, radius});

        GuardAreaBean bean = new GuardAreaBean();
        bean.setSensorId(sensorId);
        bean.setLongitude(longitude);
        bean.setLatitude(latitude);
        bean.setRadius(radius);
        bean.setTitle(title);
        bean.setType(type);
        bean.setStartCron(startCron);
        bean.setEndCron(endCron);

        long timestamp = System.currentTimeMillis() / 1000;
        bean.setCrtTime(timestamp);
        bean.setChgTime(timestamp);
        bean.setFlag(GuardAreaDao.Flag.ACTIVATED_FLAG.getCode());

        bean.setInOut(0);
        bean.setInOutTime(timestamp);

        // 获取并设置trueId 围栏编号只能设置1,2
        // 新增围栏编号都设置为 1， 然后修改已经存在的围栏编号为2， 保证围栏编号只为1,2

        try {
            bean.setTrueId(guardAreaDao.getTrueId(sensorId));
        } catch (DaoException e) {
            if ("More then two Guardarea".equals(e.getMessage())) {
                throw new BusinessException(
                        BusinessErrorConst.ExceededLimit,
                        "Guardarea number more then tow.");
            }
        }

        guardAreaDao.save(bean);

        logger.debug("gdId: {}", bean.getGdId());

        return bean.getGdId();
    }

    @Override
    public GuardAreaBean removeGuardArea(
            String sensorId,
            Long guardAreaId) throws BusinessException, DaoException {

        logger.debug(".deleteGuardArea(sensorId={}, guardAreaId={}.)",
                sensorId,
                guardAreaId);

        GuardAreaBean bean = guardAreaDao.get(GuardAreaBean.class, guardAreaId);
        if (bean != null && bean.getSensorId().equals(sensorId)) {
            guardAreaDao.delete(guardAreaId);

            //try {
            //	afterGuardAreaAction.afterGuardAreaRemoved(bean.getGdId(),
            //		sensorId,
            //		bean.getLongitude(),
            //		bean.getLatitude(),
            //		bean.getRadius());
            //} catch (Exception e) {
            //	//TODO: send a warning to monitor
            //	logger.error(Utils.getThrowableInfo(e));
            //}
        } else {
            throw new BusinessException(
                    BusinessErrorConst.Unauthorized,
                    "unauthorized sensorId, guard area id is not bound with sensor id.");
        }

        return bean;
    }

    @Override
    public void enableGps(String sensorId, Boolean enable)
            throws BusinessException, DaoException {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveSOS(SOSBean sosBean) throws DaoException {
        logger.debug(".saveSOS({})",
                JSONObject.fromObject(sosBean).toString());
        sosDao.save(sosBean);
    }

    @Override
    public List<SOSBean> getSOSList(String sensorId, Date startTime, Date endTime)
            throws BusinessException, DaoException {
        logger.debug(".getSOSList(sensorId={},start={},end={})",
                new Object[]{sensorId, startTime, endTime});
        return sosDao.get(sensorId, startTime, endTime);
    }

    @Override
    public void saveSensorConfiguration(SensorConfigurationBean scBean) throws BusinessException, DaoException {
        logger.debug(".saveSensorConfiguration({})",
                JSONObject.fromObject(scBean).toString());
        configurationDao.save(scBean);
    }

    @Override
    public SensorConfigurationBean getSensorConfiguration(String sensorId) throws BusinessException, DaoException {

        logger.debug(".getSensorConfiguration(sensorId={})",
                new Object[]{sensorId});

        return configurationDao.get(sensorId);
    }

    @Override
    public List<SOSBean> getSOSListByType(String sensorId, Date startTime, Date endTime, int type)
            throws BusinessException, DaoException {

        logger.debug(".getSOSList(sensorId={},start={},end={},type={}.)",
                new Object[]{sensorId, startTime, endTime, type});

        return sosDao.getByType(sensorId, startTime, endTime, type);
    }

    @Override
    public String getGuardAreaId(String sensorId, int trueId) throws BusinessException, DaoException {

        logger.debug(".getGuardAreaId(sensorId={},trueId={}.)",
                new Object[]{sensorId, trueId});

        return guardAreaDao.getOriginalId(sensorId, trueId);
    }

    @Override
    public void updateImsiAndIccid(String sensorId, String imsi, String iccid)
            throws BusinessException, DaoException {

        logger.debug(".updateImsiAndIccid(sensorId={},imsi={},iccid={}.)",
                new Object[]{sensorId,
                        imsi,
                        iccid});
        configurationDao.updateImsiAndIccid(sensorId, imsi, iccid);

    }

    @Override
    public void setPosition(String sensorId, boolean isPosition)
            throws DaoException, CacheException {

        logger.debug(".setPosition({},{})", sensorId, isPosition);

        SensorOnlineBean onlineBean = onlineDao.get(sensorId);
        if (onlineBean != null) {
            onlineDao.updatePosition(sensorId, isPosition);
        }

        String key = "SENSOR-POSITION-" + sensorId;
        cacheService.set(key, isPosition);

    }

    @Override
    public Boolean isPosition(String sensorId)
            throws BusinessException, DaoException, CacheException {

        logger.debug(".isPosition({})", sensorId);

        String key = "SENSOR-POSITION-" + sensorId;
        Boolean isPosition = false;

        do {
            Object obj = cacheService.get(key);
            if (obj != null) {
                isPosition = ((Boolean) obj).booleanValue();
                break;
            }

            SensorOnlineBean onlineBean = onlineDao.get(sensorId);
            if (onlineBean != null) {
                isPosition = onlineBean.getPosition();
                cacheService.set(key, new Boolean(isPosition));
            }

        } while (false);

        logger.debug("{} : {}.", key, isPosition);
        if (isPosition != null && isPosition.booleanValue() == true) {
            return true;
        }

        return false;
    }

    @Override
    public List<SOSBean> getSOSList(List<String> sensorIds, Date startTime,
                                    Date endTime) throws BusinessException, DaoException {

        logger.debug(".getSOSList(sensorIds={},start={},end={})",
                new Object[]{sensorIds,
                        startTime,
                        endTime});

        return sosDao.get(sensorIds, startTime, endTime);
    }

    @Override
    public void updateGuardAreaTrueId(long guardId, int trueId) throws BusinessException, DaoException {

        logger.debug(".updateGuardAreaTrueId(guardId={}, trurId={}.)",
                new Object[]{guardId, trueId});

        guardAreaDao.updateTrueId(guardId, trueId);

    }

    @Override
    public long getEveryDayOnlineCount() throws BusinessException, DaoException, ParseException {
        return onlineDao.getOnlineCount();
    }

    @Override
    public long getEveryDayActivateCount()
            throws BusinessException, DaoException, ParseException {
        return onlineDao.getActivateCount();
    }

    @Override
    public void updateGuardInOutStatus(long guardId, int inOut, long inOutTime)
            throws BusinessException, DaoException {
        guardAreaDao.updateInOut(guardId, inOut, inOutTime);
    }

    @Override
    public List<MessageBean> getSOSMessages(long userId, List<String> sensorIds, int from, int to, List<String> types)
            throws BusinessException, DaoException {
        return messageDao.getSensorMessages(userId, sensorIds, from, to, types);
    }


    @Override
    public void updateChatSwitch(String sensorId, int cSwitch)
            throws BusinessException, DaoException {
        configurationDao.updateChatSwitch(sensorId, cSwitch);
    }

    @Override
    public String saveSensorMessage(String sensorId,
                                    dudu.service.core.MessageBean pushMsg, String serialNumber)
            throws BusinessException, DaoException {

        Date time = new Date();
        String timestamp = sdf.format(time);
        String msgId = String.format("%s%s", sensorId, timestamp);
        pushMsg.setMsgId(msgId);
        SensorMessageBean daoMsgBean = new SensorMessageBean();
        daoMsgBean.setMsgId(msgId);
        daoMsgBean.setSerialNumber(serialNumber);
        daoMsgBean.setBody(JSONObject.fromObject(pushMsg).toString());
        daoMsgBean.setSend(false);
        daoMsgBean.setDown(false);
        daoMsgBean.setExpire(false);
        daoMsgBean.setType(pushMsg.getType());
        daoMsgBean.setCrtTime(time.getTime() / 1000);
        daoMsgBean.setChgTime(time.getTime() / 1000);
        sensorMessageDao.save(daoMsgBean);

        return msgId;
    }

    @Override
    public void updateSensorMessageSended(String sensorId, String serialNumber)
            throws BusinessException, DaoException {
        sensorMessageDao.updateSendedStatus(sensorId, serialNumber);
    }

    @Override
    public SensorMessageBean getSendSensorMessage(String sensorId)
            throws BusinessException, DaoException {
        return sensorMessageDao.getDownMessage(sensorId);
    }

    @Override
    public SensorMessageBean getReSendSensorMessage(String sensorId,
                                                    String serialNumber) throws BusinessException, DaoException {
        return sensorMessageDao.getSensorMessage(sensorId, serialNumber);
    }

    @Override
    public void setSpaceStatus(String sensorId, boolean isFull) throws CacheException {

        logger.debug(".setSpaceStatus({},{})", sensorId, isFull);

        String key = "SENSOR-SPACE-STATUS-" + sensorId;
        cacheService.set(key, isFull);
    }

    @Override
    public Boolean isSpaceFull(String sensorId) throws CacheException {

        logger.debug(".isSpaceFull({})", sensorId);

        String key = "SENSOR-SPACE-STATUS-" + sensorId;
        Boolean isFull = false;

        Object obj = cacheService.get(key);
        if (obj != null) {
            isFull = ((Boolean) obj).booleanValue();
        }

        return isFull;
    }

    @Override
    public void updateDown(String sensorId, String serialNumber, boolean flag)
            throws DaoException {
        sensorMessageDao.updateDown(sensorId, serialNumber, flag);
    }

    @Override
    public SensorMessageBean getDownSensorMessage(String sensorId)
            throws BusinessException, DaoException {
        return sensorMessageDao.getDownMessage(sensorId);
    }

    @Override
    public void setRecordRespLastTime(String sensorId) throws CacheException {

        String key = "SENSOR-RECORDRESP-LASTTIME" + sensorId;
        Long lTime = System.currentTimeMillis() / 1000;
        cacheService.set(key, lTime);
        logger.debug(".setRecordRespLastTime(sensorId={}, lTime={})", sensorId, lTime);
    }

    @Override
    public void setRecordRespLastTimeCompeleted(String sensorId)
            throws CacheException {

        String key = "SENSOR-RECORDRESP-LASTTIME" + sensorId;
        Long lTime = System.currentTimeMillis() / 1000 - 1 * 24 * 60 * 60; // 一天
        cacheService.set(key, lTime);
        logger.debug(".setRecordRespLastTimeCompeleted(sensorId={}, lTime={})", sensorId, lTime);

    }

    @Override
    public Long getRecordRespLastTime(String sensorId) throws CacheException {

        String key = "SENSOR-RECORDRESP-LASTTIME" + sensorId;

        Long ltime = null;

        Object obj = cacheService.get(key);

        if (obj != null) {
            ltime = (Long) obj;
        } else {
            ltime = System.currentTimeMillis() / 1000 - 301; // 20秒控制APP第一次下发时间
        }

        logger.debug(".getRecordRespLastTime(sensorId={}, lTime={})", sensorId, ltime);

        return ltime;
    }

    @Override
    public Long updateGuardArea(String guardId,
                                String sensorId,
                                Float longitude,
                                Float latitude,
                                Integer radius,
                                String title,
                                String type,
                                String startCron,
                                String endCron) throws BusinessException, DaoException {

        logger.debug(".updateGuardArea(guardId={}, sensorId={},lo={},la={},r={})",
                new Object[]{guardId, sensorId, longitude, latitude, radius});

        long timestamp = System.currentTimeMillis() / 1000;

        GuardAreaBean bean = new GuardAreaBean();
        bean.setGdId(Long.parseLong(guardId));
        bean.setSensorId(sensorId);
        bean.setLongitude(longitude);
        bean.setLatitude(latitude);
        bean.setRadius(radius);
        bean.setTitle(title);
        bean.setType(type);
        bean.setStartCron(startCron);
        bean.setEndCron(endCron);
        bean.setChgTime(timestamp);
        bean.setInOut(0);
        bean.setInOutTime(timestamp);

        guardAreaDao.update(bean);

        logger.debug("update gdId: {}", bean.getGdId());

        return bean.getGdId();

    }

    /**
     * 获取所有设备IMEI号，由逗号分隔,eg. 12323123,534234,323123,......
     *
     * @return
     * @throws DaoException
     */
    public SensorAccountData getAllSensorId(Page page) throws DaoException {
        StringBuffer buffer = new StringBuffer();
        List<SensorConfigurationBean> beans = new ArrayList<SensorConfigurationBean>();
        SensorAccountData data = new SensorAccountData();
        configurationDao.getSensorList(beans, page);
        Iterator iterator = beans.iterator();
        while (iterator.hasNext()) {
            buffer.append(((SensorConfigurationBean) iterator.next()).getSensorId() + ",");
        }
        //删除最后一个逗号
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        if (logger.isDebugEnabled()) {
            int len = buffer.length();
            if (buffer.length() > 50) {
                len = 50;
            }
            logger.debug("所有设备IMEI号：" + buffer.substring(0, len) + "......");
        }

        return data.setAccountIds(buffer.toString()).setPage(page);
    }

    //根据围栏id获取围栏信息
    @Override
    public GuardAreaBean getGuardArea(Long guId)
            throws BusinessException, DaoException {

        logger.debug(".getGuardArea(guId={})", guId);
        return guardAreaDao.getById(guId).get(0);

    }

    @Override
    public Long saveTeaGuardArea(
            String sensorId,
            Float longitude,
            Float latitude,
            Integer radius,
            String title,
            String type,
            String startCron,
            String endCron) throws BusinessException, DaoException {

        logger.debug(".addGuardArea(sensorId={},lo={},la={},r={})",
                new Object[]{sensorId, longitude, latitude, radius});

        GuardAreaBean bean = new GuardAreaBean();
        bean.setSensorId(sensorId);
        bean.setLongitude(longitude);
        bean.setLatitude(latitude);
        bean.setRadius(radius);
        bean.setTitle(title);
        bean.setType(type);
        bean.setStartCron(startCron);
        bean.setEndCron(endCron);

        long timestamp = System.currentTimeMillis() / 1000;
        bean.setCrtTime(timestamp);
        bean.setChgTime(timestamp);
        bean.setFlag(GuardAreaDao.Flag.ACTIVATED_FLAG.getCode());

        bean.setInOut(0);
        bean.setInOutTime(timestamp);

        List<GuardAreaBean> schoolGuard = guardAreaDao.getSchoolGuardIdBySensorId(sensorId);
        if(schoolGuard.size()==0){
            //判断是否存在老师围栏，存在就修改 ,不存在就添加，如果已经有两个围栏就修改其中一个
            List<GuardAreaBean> guardAreaBeans = guardAreaDao.get(sensorId, GuardAreaDao.Flag.ACTIVATED_FLAG);
            if (guardAreaBeans.size() == 2) {
                GuardAreaBean guardAreaBean = guardAreaBeans.get(0);
                bean.setGdId(guardAreaBean.getGdId());
                guardAreaDao.update(bean);
            } else if (guardAreaBeans.size() < 2) {
                try {
                    bean.setTrueId(guardAreaDao.getTrueId(sensorId));
                } catch (DaoException e) {
                    if ("More then two Guardarea".equals(e.getMessage())) {
                        throw new BusinessException(
                                BusinessErrorConst.ExceededLimit,
                                "Guardarea number more then tow.");
                    }
                }
                guardAreaDao.save(bean);
            }
        }else {
            bean.setGdId(schoolGuard.get(0).getGdId());
            guardAreaDao.update(bean);
        }

        logger.debug("gdId: {}", bean.getGdId());
        return bean.getGdId();
    }

    @Override
    public List<GuardAreaBean> getTeaGuardAreaList(String sensorId)
            throws BusinessException, DaoException {

        logger.debug(".getGuardAreaList(sensorId={})", sensorId);

        return guardAreaDao.getSchoolGuardIdBySensorId(sensorId);
    }

    @Override
    public String getTeaGuardAreaId(String sensorId, int trueId) throws BusinessException, DaoException {

        logger.debug(".getGuardAreaId(sensorId={},trueId={}.)",
                new Object[]{sensorId, trueId});

        return guardAreaDao.getTeaOriginalId(sensorId, trueId);
    }
}
