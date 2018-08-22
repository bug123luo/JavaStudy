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

import dudu.service.common.*;
import dudu.service.core.utils.MD5;
import dudu.service.dao.*;
import dudu.service.dao.bean.*;
import dudu.service.dao.bean.info.StudentCheckInfo;
import dudu.service.dto.Page;
import dudu.service.dto.SensorAccountData;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SensorCheckServiceImpl implements SensorCheckService {

    private static Logger logger = LoggerFactory.getLogger(SensorCheckServiceImpl.class);
    private TeacherClassDao teacherClassDao;
    private StudentDao studentDao;
    private SensorCheckDao sensorCheckDao;
    private TeacherDao teacherDao;
    private AccountDao accountDao;
    private ClassDao classDao;
    private GradeDao gradeDao;

    public void setTeacherClassDao(TeacherClassDao teacherClassDao) {
        this.teacherClassDao = teacherClassDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setSensorCheckDao(SensorCheckDao sensorCheckDao) {
        this.sensorCheckDao = sensorCheckDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setClassDao(ClassDao classDao) {
        this.classDao = classDao;
    }

    public void setGradeDao(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<StudentCheckInfo> getSensorCheck(Long teacherId, long time, String state) throws BusinessException {

        logger.debug(".getSensorCheck:teacherId:({}),time:({})", teacherId, time);

        List<StudentCheckInfo> studentCheckInfos = new ArrayList<StudentCheckInfo>();

        try {
            //通过老师手机号获得老师信息（注：目前业务需求一个老师一个班级，故直接去第一条）
            TeacherClassBean teacherClassBeen = teacherClassDao.getByTeaId(teacherId).get(0);
            //通过班级班级id获得班级所有学生信息
            Integer classId = teacherClassBeen.getClassId();
            logger.debug("getClassId：({})", classId);
            //通过SensorId获取所有学生在线状态
            List<StudentBean> studentBeanList = studentDao.getByClass(classId);
            logger.debug("成功获取班级学生信息");
            //封装考勤信息
            for (StudentBean student : studentBeanList) {
                StudentCheckInfo studentCheckInfo = new StudentCheckInfo();
                String sensorId = student.getSensorId();
                studentCheckInfo.setSensorId(sensorId);
                studentCheckInfo.setName(student.getName());
                studentCheckInfo.setAvatar(student.getAvatar());
                SensorCheckBean sensorCheckBean = sensorCheckDao.getByCheckDate(sensorId, time, state);
                if (sensorCheckBean == null) {
                    studentCheckInfo.setCheckStatus("2");
                } else {
                    studentCheckInfo.setCheckStatus(sensorCheckBean.getIntoStatus());
                    studentCheckInfo.setIntoTime(sensorCheckBean.getIntoTime());
                    studentCheckInfo.setOutTime(sensorCheckBean.getOutTime());
                }
                studentCheckInfos.add(studentCheckInfo);
            }
            return studentCheckInfos;
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    e.getMessage(),
                    e.getCause());
        }
    }

    @Override
    public Long authenticate(String phoneNumber, String password)
            throws BusinessException {

        logger.debug(".authenticate(uid={}, p={})", phoneNumber, password);
        System.out.println(String.format(".authenticate(uid=%s, p=%s)", phoneNumber, password));

        try {
            AccountBeanEx teacherBean = accountDao.get(phoneNumber);
            //AccountBean accountBean = accountDao.get(phoneNumber, password);
            //AccountBean accountBean = accountDao.get(phoneNumber);

            if (teacherBean == null) {
                throw new BusinessException(
                        BusinessErrorConst.IncorrectAccountOrPassword,
                        "Incorrect account or password");
            }

           /* String pwd = password.toUpperCase();

            logger.debug(".authenticate(pwd={})", pwd);

            System.out.println(String.format(".authenticate(pwd=%s)", pwd));

            String newmd5 = MD5.getMessageDigest((pwd).getBytes());
            String dbPwd = teacherBean.getPassword();

            logger.debug(".authenticate(newmd5={}, dbPwd={})", newmd5, dbPwd);
*/
            String dbPwd = teacherBean.getPasswd();
            System.out.println(String.format(".authenticate(password=%s, dbPwd=%s)", password, dbPwd));


            if (!password.equalsIgnoreCase(dbPwd)) {
                throw new BusinessException(
                        BusinessErrorConst.IncorrectAccountOrPassword,
                        "Incorrect account or password");
            }

            return teacherBean.getId();

        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }

    @Override
    public ClassBean getClassByteaId(Long teacherId) throws BusinessException {

        logger.debug(".getClassByteaId:teacherId:({})", teacherId);

        String className = null;
        try {
            //通过老师手机号获得老师信息（注：目前业务需求一个老师一个班级，故直接去第一条）
            List<TeacherClassBean> teacherClassBeans = teacherClassDao.getByTeaId(teacherId);
            if (teacherClassBeans.size() == 0) {
                throw new BusinessException(
                        BusinessErrorConst.IsNoTeacher,
                        "The user is not a teacher");
            }
            TeacherClassBean teacherClassBeen = teacherClassBeans.get(0);
            //通过班级班级id获得班级所有学生信息
            Integer classId = teacherClassBeen.getClassId();
            ClassBean classBean = classDao.getById(classId);
            GradeBean gradeBean = gradeDao.getById(teacherClassBeen.getGradeId());
            className = gradeBean.getName() + classBean.getName();
            classBean.setName(className);
            return classBean;
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    e.getMessage(),
                    e.getCause());
        }
    }

    @Override
    public List<String> getClassStudent(Integer classId) throws BusinessException {

        List<StudentBean> studentBeanList = null;
        try {
            studentBeanList = studentDao.getByClass(classId);

            logger.debug("成功获取班级学生信息");

            List<String> sensorIds = new ArrayList<String>();
            //封装考勤信息
            for (StudentBean student : studentBeanList) {
                StudentCheckInfo studentCheckInfo = new StudentCheckInfo();
                String sensorId = student.getSensorId();

                sensorIds.add(sensorId);
            }

            return sensorIds;

        } catch (DaoException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    e.getMessage(),
                    e.getCause());
        }
    }

    @Override
    public TeacherClassBean getTeacherBySenid(String sensorId) throws BusinessException {

        logger.debug(".getTeacherBySenid:sensorId:({})", sensorId);

        TeacherClassBean classBean = null;
        try {
            List<StudentBean> studentBeans = studentDao.getBySensorId(sensorId);
            if (studentBeans.size() != 0) {
                StudentBean studentBean = studentBeans.get(0);
                List<TeacherClassBean> classBeans = teacherClassDao.getByClassId(studentBean.getClassId());
                //介于目前每班只有一个老师，故去第一条数据
                classBean = classBeans.get(0);
            }
            return classBean;
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    e.getMessage(),
                    e.getCause());
        }
    }

    //获取单个学生考勤数据
    public SensorCheckBean getStudentCheck(String sensorId, long time, String state)throws BusinessException {
        try {
            return sensorCheckDao.getByCheckDate(sensorId, time, state);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    e.getMessage(),
                    e.getCause());
        }
    }

}