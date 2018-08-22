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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dudu.service.dao.bean.SensorOnlineBean;

public class SensorOnlineDao extends DuduDaoSupport {

	public SensorOnlineDao() {
		
	}
	
	public void save(SensorOnlineBean onlineBean) throws DaoException {
		super.save(onlineBean);
	}
	
	public void update(String sensorId, Boolean online) throws DaoException {
		
		String hql = String.format(
			"UPDATE SensorOnlineBean sob SET " + 
			"sob.online=%b," + 
			"sob.chgTime=%d " + 
			"WHERE sob.sensorId='%s'" , 
			online,
			Calendar.getInstance().getTime().getTime() / 1000,
			sensorId);
		
		super.update(hql);
	}
	
	public SensorOnlineBean get(String sensorId) throws DaoException {
		
		return super.get(SensorOnlineBean.class, sensorId);
	}
	
	public List<SensorOnlineBean> get(List<String> sensorIds) throws DaoException {
		
		StringBuilder sensorIdCollection = new StringBuilder();
		sensorIdCollection.append("(");
		for (String id : sensorIds) {
			sensorIdCollection.append(id);
			sensorIdCollection.append(",");
		}
		sensorIdCollection.deleteCharAt(sensorIdCollection.lastIndexOf(","));
		sensorIdCollection.append(")");
		
		String hql = String.format(
			"FROM SensorOnlineBean sob WHERE sob.sensorId in %s",
			sensorIdCollection.toString());
		
		return super.query(hql);
	} 
	
	// 更改设备定位状态
	public void updatePosition(String sensorId, Boolean position) throws DaoException {
		
		String hql = String.format(
			"UPDATE SensorOnlineBean sob SET " + 
			"sob.position=%b," + 
			"sob.chgTime=%d " + 
			"WHERE sob.sensorId='%s'" , 
			position,
			Calendar.getInstance().getTime().getTime() / 1000,
			sensorId);
		
		super.update(hql);
	}
	
	// 获取设备每天在线数
	public long getOnlineCount() throws DaoException, ParseException {

		String hql = String.format(
			"SELECT count(*) FROM SensorOnlineBean sob " + 
			"WHERE sob.chgTime>%d ",
			getZeroDate().getTime()/1000);
		
		return  (long) super.uniqueResult(hql);
	}
	
	// 获取设备每天激活数
	public long getActivateCount() throws DaoException, ParseException {
		String hql = String.format(
			"SELECT count(*) FROM SensorOnlineBean sob " + 
			"WHERE sob.crtTime>%d ",
			getZeroDate().getTime()/1000);
		
		return  (long) super.uniqueResult(hql);
	}
	
	
	private Date getZeroDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String date = String.format("%d-%d-%d %s", year, month, day, " 00:00:00");
		
		return sdf.parse(date);
	}
	
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String date = String.format("%d-%d-%d %s", year, month, day, " 00:00:00");
		Date zoreDate = sdf.parse(date);
		
		System.err.println(zoreDate.getTime()/1000);
	}
}
