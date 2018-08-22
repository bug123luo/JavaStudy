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

import dudu.service.dao.bean.GradeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GradeDao extends DuduDaoSupport {

    private static Logger logger = LoggerFactory.getLogger(GradeDao.class);

    public GradeDao() {

    }

    public void saveOrUpdate(GradeBean gradeBean) throws DaoException {
        super.saveOrUpdate(gradeBean);
    }

    public GradeBean getById(Integer id)
            throws DaoException {

        String hql = String.format("FROM GradeBean gr WHERE " +
                        "gr.id=\'%s\'",
                id);

        logger.debug(".getById hql = {}", hql);
        return (GradeBean) super.uniqueResult(hql);
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
