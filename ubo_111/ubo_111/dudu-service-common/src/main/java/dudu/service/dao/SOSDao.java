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
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.dao.bean.SOSBean;

public class SOSDao extends DuduDaoSupport {

	private static Logger logger = LoggerFactory.getLogger(SOSDao.class);
	
	public SOSDao() {
		
	}
	
	public void save(SOSBean sosBean) throws DaoException {
		super.save(sosBean);
	}
	
	public List<SOSBean> get(String sensorId, Date startTime, Date endTime)
			throws DaoException {
		
			long sosStart = (long)startTime.getTime()/1000;
			long sosEnd = (long)endTime.getTime()/1000;
			
			String hql = String.format("FROM SOSBean sb WHERE " + 
				"sb.sensorId = \'%s\' AND sb.crtTime BETWEEN %d AND %d ORDER BY sb.crtTime DESC",
				sensorId,
				sosStart,
				sosEnd);
			
			logger.debug(".getList<SOSBean> hql = {}", hql);
			
			return super.query(hql);
	}
	
	
	public List<SOSBean> get(String sensorId, long startTime, long endTime)
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
		
			long sosStart = (long)startTime.getTime()/1000;
			long sosEnd = (long)endTime.getTime()/1000;
			
			String hql = String.format("FROM SOSBean sb WHERE " +
				"sb.sensorId = \'%s\' AND sb.crtTime BETWEEN %d AND %d AND sb.type = %d ORDER BY sb.crtTime DESC",
				sensorId,
				sosStart,
				sosEnd,
				type);
			
			logger.debug(".getByType<SOSBean> hql = {}", hql);
			
			return super.query(hql);
	}
	
	public List<SOSBean> get1(List<String> sensorIdList, Date startTime, Date endTime) throws DaoException {
		
		long sosStart = (long)startTime.getTime()/1000;
		long sosEnd = (long)endTime.getTime()/1000;
		
		StringBuffer idsBuffer = new StringBuffer();
		idsBuffer.append("(");
		Iterator<String> iterator = sensorIdList.iterator();
		while (iterator.hasNext()) {
			idsBuffer.append("\'");
			idsBuffer.append(iterator.next());
			idsBuffer.append("\'");
			if (iterator.hasNext()) {
				idsBuffer.append(",");
			} else {
				idsBuffer.append(")");
			}
		}
		
		String idCollection = idsBuffer.toString();
		
		String hql = String.format("FROM SOSBean sb WHERE " +
			"sb.sensorId in %s AND sb.crtTime BETWEEN %d AND %d ORDER BY sb.crtTime DESC",
			idCollection,
			sosStart,
			sosEnd);
		
		return super.query(hql);
	}
	
	// 获取设备最后2天的告警信息
	public List<SOSBean> get(List<String> sensorIdList, Date startTime, Date endTime) throws DaoException {
		
		List<SOSBean> result = new ArrayList<SOSBean>();
		
		for (String sensorId: sensorIdList) {
			long eTime = getSOSLastTime(sensorId);
			long sTime = eTime - 2 * 24 * 60 * 60;
		
			List<SOSBean> list = get(sensorId, sTime, eTime);
			if (list != null && !list.isEmpty()) {
				result.addAll(list);
			}
		}
		
		Collections.sort(result);
		
		return  result;
		
	}
	
	private long getSOSLastTime(String sensorId) throws DaoException {
		
		String hql = String.format("SELECT MAX(sb.crtTime) FROM SOSBean sb WHERE sb.sensorId = \'%s\'",
			sensorId);
		
		Object obj = super.uniqueResult(hql);
		
		if (obj == null) {
			return System.currentTimeMillis()/1000;
		} else {
			return (long)obj;
		}
	}
	
}
