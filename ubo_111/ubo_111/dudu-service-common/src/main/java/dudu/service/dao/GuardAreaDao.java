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
import java.util.Iterator;
import java.util.List;

import org.jboss.netty.buffer.ChannelBufferOutputStream;

import dudu.service.dao.bean.GuardAreaBean;

public class GuardAreaDao extends DuduDaoSupport {

	public GuardAreaDao() {

	}

	public void save(GuardAreaBean bean) throws DaoException {
		super.save(bean);
	}

	public void delete(long gdId) throws DaoException {

		String hql = String.format(
			"UPDATE GuardAreaBean gab SET gab.flag=%d WHERE gab.gdId=%d",
			GuardAreaDao.Flag.DELETED_FLAG.getCode(), 
			gdId);

		super.update(hql);
	}
	

	public void update(GuardAreaBean bean) throws DaoException {
		
		String hql = String.format(
			"UPDATE GuardAreaBean gab SET gab.flag=%d, " +
		    "gab.longitude=%f,      " +
			"gab.latitude=%f,       " +
		    "gab.radius=%d,         " + 
			"gab.title=\'%s\',      " +
			"gab.type=\'%s\',       " + 
		    "gab.startCron=\'%s\',  " + 
			"gab.endCron=\'%s\',    " +
		    "gab.chgTime=%d,        " +
			"gab.inOut=%d,          " + 
		    "gab.inOutTime=%d       " +
			"WHERE gab.gdId=%d AND gab.sensorId=\'%s\' ",
			GuardAreaDao.Flag.ACTIVATED_FLAG.getCode(), 
			bean.getLongitude(),
			bean.getLatitude(),
			bean.getRadius(),
			bean.getTitle(),
			bean.getType(),
			bean.getStartCron(),
			bean.getEndCron(),
			bean.getChgTime(),
			bean.getInOut(),
			bean.getInOutTime(),
			bean.getGdId(),
			bean.getSensorId());
		
		super.update(hql);
	}

	public List<GuardAreaBean> get(String sensorId, GuardAreaDao.Flag flag)
		throws DaoException {

		String hql = String.format(
			"FROM GuardAreaBean gab WHERE gab.sensorId=\'%s\' AND gab.flag=%d",
			sensorId, 
			flag.getCode());

		return super.query(hql);
	}

	public List<GuardAreaBean> getById(Long guaId)
			throws DaoException {

		String hql = String.format(
				"FROM GuardAreaBean gab WHERE gab.gdId=\'%s\'",
				guaId);

		return super.query(hql);
	}

	public List<GuardAreaBean> getSchoolGuardIdBySensorId(String sensorId)
		throws DaoException {

		GuardAreaBean guardAreaBean=null;
		String hql = String.format(
			"FROM GuardAreaBean gab WHERE gab.title=\'%s\' AND gab.sensorId=\'%s\' AND gab.flag=%d",
			"学校围栏",
			sensorId,
			GuardAreaDao.Flag.ACTIVATED_FLAG.getCode());

		return super.query(hql);

	}

	public List<GuardAreaBean> get(List<String> sensorIds, Flag activatedFlag)
		throws DaoException {

		StringBuffer idsBuffer = new StringBuffer();
		idsBuffer.append("(");
		Iterator<String> iterator = sensorIds.iterator();
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

		String hql = String.format(
			"FROM GuardAreaBean gab WHERE gab.sensorId in %s AND gab.flag=%d",
			idCollection, 
			activatedFlag.getCode()
			);
		return super.query(hql);
	}

	public void updateTrueId(long guardId, int trueId) throws DaoException {

		String hql = String.format(
			"UPDATE GuardAreaBean gab SET gab.trueId=%d WHERE gab.gdId=%d",
			trueId, 
			guardId);

		super.update(hql);
	}

	public void updateInOut(long guardId, int inOut, long inOutTime)
		throws DaoException {

		String hql = String.format(
			"UPDATE GuardAreaBean gab SET gab.inOut=%d, gab.inOutTime=%d WHERE gab.gdId=%d",
			inOut, 
			inOutTime, 
			guardId);

		super.update(hql);
	}

	// 只保留 1,2的围栏编号
	public synchronized  int getTrueId(String sensorId) 
			throws DaoException {

		String hql = String.format(
			"FROM GuardAreaBean gab WHERE gab.sensorId=\'%s\' AND gab.flag=%d",
			sensorId,
			GuardAreaDao.Flag.ACTIVATED_FLAG.getCode());

		List<GuardAreaBean> list = super.query(hql);

		if (list != null && list.size() > 2) {
			throw new DaoException("More then two Guardarea");
		}

		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(2);

		if (list != null && !list.isEmpty()) {
			for (GuardAreaBean gab : list) {
				if (gab.getTrueId() > 0) {
					idList.remove(new Integer(gab.getTrueId()));
				}
			}
		}

		if (idList.size() == 0) {
			throw new DaoException("More then two Guardarea");
		}

		return idList.get(0);
	}

	public String getOriginalId(String sensorId, int trueId)
		throws DaoException {
		String id = "";

		String hql = String.format(
			"FROM GuardAreaBean gab WHERE gab.sensorId=\'%s\' AND gab.flag=%d AND gab.trueId=%d ",
			sensorId,
			GuardAreaDao.Flag.ACTIVATED_FLAG.getCode(),
			trueId);

		List<GuardAreaBean> list = super.query(hql);
		if (list != null && !list.isEmpty()) {
			id = list.get(0).getGdId() + "";
		}

		return id;
	}

	public static enum Flag {
		DELETED_FLAG((byte)0x00),
		ACTIVATED_FLAG((byte)0x01),
		CANCELED_FLAG((byte)0x02),
		CHECKAM_FLAG((byte)0x03),
		CHECKPM_FLAG((byte)0x04);

		private byte code;

		private Flag(byte code) {
			this.code = code;
		}

		static Flag mkFlag(byte code) {
			for (Flag f : Flag.values()) {
				if (code == f.code)
					return f;
			}

			return null;
		}

		public byte getCode() {
			return this.code;
		}

		void write(ChannelBufferOutputStream bout) throws Exception {
			bout.writeByte(this.code);
		}
	}

	public long getBySensorId(String sensorId,byte flag)
			throws DaoException {

		long gid = 0;

		String hql = String.format(
				"FROM GuardAreaBean gab WHERE  gab.sensorId=\'%s\' AND gab.flag=%d",
				sensorId,
				flag);

		List<GuardAreaBean> list = super.query(hql);

		if (list != null && !list.isEmpty()) {
			GuardAreaBean gab = list.get(0);
			gid = gab.getGdId();
		}

		return gid;
	}

	public String getTeaOriginalId(String sensorId, int trueId)
			throws DaoException {
		String id = "";

		String hql = String.format(
				"FROM GuardAreaBean gab WHERE gab.sensorId=\'%s\' AND gab.flag=%d AND gab.trueId=%d ",
				sensorId,
				GuardAreaDao.Flag.CHECKAM_FLAG.getCode(),
				trueId);

		List<GuardAreaBean> list = super.query(hql);
		if (list != null && !list.isEmpty()) {
			id = list.get(0).getGdId() + "";
		}

		return id;
	}

	public void updateTeacher(GuardAreaBean bean) throws DaoException {

		String hql = String.format(
				"UPDATE GuardAreaBean gab SET gab.flag=%d, " +
						"gab.longitude=%f,      " +
						"gab.latitude=%f,       " +
						"gab.radius=%d,         " +
						"gab.title=\'%s\',      " +
						"gab.type=\'%s\',       " +
						"gab.startCron=\'%s\',  " +
						"gab.endCron=\'%s\',    " +
						"gab.chgTime=%d,        " +
						"gab.inOut=%d,          " +
						"gab.inOutTime=%d       " +
						"WHERE gab.gdId=%d AND gab.sensorId=\'%s\' ",
				bean.getFlag(),
				bean.getLongitude(),
				bean.getLatitude(),
				bean.getRadius(),
				bean.getTitle(),
				bean.getType(),
				bean.getStartCron(),
				bean.getEndCron(),
				bean.getChgTime(),
				bean.getInOut(),
				bean.getInOutTime(),
				bean.getGdId(),
				bean.getSensorId());

		super.update(hql);
	}

	// 如果是教师设置的围栏，当学校围栏已设置便更新，未设置就保存。保证数据库只有两天围栏信息 1,2的围栏编号
	public synchronized  int getTeaTrueId(String sensorId)
			throws DaoException {

		String hql = String.format(
				"FROM GuardAreaBean gab WHERE gab.sensorId=\'%s\' AND gab.flag=%d",
				sensorId,
				GuardAreaDao.Flag.ACTIVATED_FLAG.getCode());

		List<GuardAreaBean> list = super.query(hql);

		if (list != null && list.size() > 2) {
			throw new DaoException("More then two Guardarea");
		}

		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(2);

		if (list != null && !list.isEmpty()) {
			for (GuardAreaBean gab : list) {
				if (gab.getTrueId() > 0) {
					idList.remove(new Integer(gab.getTrueId()));
				}
			}
		}

		if (idList.size() == 0) {
			return 2;
		}

		return idList.get(0);
	}
}
