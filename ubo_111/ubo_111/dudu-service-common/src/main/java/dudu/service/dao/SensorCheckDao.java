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

import dudu.service.dao.bean.SensorCheckBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SensorCheckDao extends DuduDaoSupport {

    private static Logger logger = LoggerFactory.getLogger(SensorCheckDao.class);

    public SensorCheckDao() {

    }

    public void saveOrUpdate(SensorCheckBean sensorCheckBean) throws DaoException {
        super.saveOrUpdate(sensorCheckBean);
    }

    public SensorCheckBean getByCheckDate(String sensorId,long time, String state)
            throws DaoException {

        String hql = String.format("FROM SensorCheckBean sc WHERE " +
                        "sc.sensorId=\'%s\' AND sc.checkDate = \'%s\' AND sc.status=\'%s\' ",
                sensorId,
                time,
                state
        );

        logger.debug(".getByCheckData hql = {}", hql);
        return (SensorCheckBean) super.uniqueResult(hql);
    }



    /*public List<SOSBean> get(String sensorId, long startTime, long endTime)
            throws DaoException {

        String hql = String.format("FROM SOSBean sb WHERE " +
                        "sb.sensorId = \'%s\' AND sb.crtTime BETWEEN %d AND %d ORDER BY sb.crtTime DESC",
                sensorId,
                startTime,
                endTime);

        logger.debug(".getList<SOSBean> hql = {}", hql);

        return super.query(hql);
    }

    public List<SOSBean> getByType(String sensorId, Date startTime, Date endTime, int type)
            throws DaoException {

        long sosStart = (long) startTime.getTime() / 1000;
        long sosEnd = (long) endTime.getTime() / 1000;

        String hql = String.format("FROM SOSBean sb WHERE " +
                        "sb.sensorId = \'%s\' AND sb.crtTime BETWEEN %d AND %d AND sb.type = %d ORDER BY sb.crtTime DESC",
                sensorId,
                sosStart,
                sosEnd,
                type);

        logger.debug(".getByType<SOSBean> hql = {}", hql);

        return super.query(hql);
    }


    private long getSOSLastTime(String sensorId) throws DaoException {
        super.update("UPD");
        String hql = String.format("SELECT MAX(sb.crtTime) FROM SOSBean sb WHERE sb.sensorId = \'%s\'",
                sensorId);

        Object obj = super.uniqueResult(hql);

        if (obj == null) {
            return System.currentTimeMillis() / 1000;
        } else {
            return (long) obj;
        }
    }*/

}
