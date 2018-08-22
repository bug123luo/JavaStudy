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

import dudu.service.dao.DaoException;
import dudu.service.dao.bean.*;
import dudu.service.dao.bean.info.StudentCheckInfo;
import dudu.service.dto.Page;
import dudu.service.dto.SensorAccountData;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SensorCheckService {

	 // 获取考勤信息
	 List<StudentCheckInfo> getSensorCheck(Long teacherId, long time, String status)  throws BusinessException;

	//教师登录验证
	Long authenticate(String phoneNumber, String password) throws BusinessException;

	//获取班级名称
	ClassBean getClassByteaId(Long teacherId) throws BusinessException;

	//获取班级学生
	List<String> getClassStudent(Integer classId) throws BusinessException;

	//根据sensor_id获取学士教师id
	TeacherClassBean getTeacherBySenid(String sensorId) throws BusinessException;

	//获取单个学生考勤数据
	public SensorCheckBean getStudentCheck(String sensorId, long time, String state)throws BusinessException;

}
