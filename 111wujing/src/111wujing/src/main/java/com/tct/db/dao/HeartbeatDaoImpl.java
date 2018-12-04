/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HeartbeatDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月8日 下午3:40:36   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.tct.codec.protocol.pojo.DeviceHeartReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartReqMessageBodyGunInfo;
import com.tct.db.mapper.AppCustomMapper;
import com.tct.db.mapper.AppDynamicDataCustomMapper;
import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.mapper.GunLocationCustomMapper;
import com.tct.db.mapper.SosMessageCustomMapper;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;
import com.tct.db.po.AppDynamicDataCustom;
import com.tct.db.po.AppGunCustom;
import com.tct.db.po.AppGunCustomQueryVo;
import com.tct.db.po.Gun;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunCustomQueryVo;
import com.tct.db.po.GunLocationCustom;
import com.tct.db.po.SosMessageCustom;
import com.tct.util.CoordinateConvertUtil;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

import lombok.extern.slf4j.Slf4j;


/**   
 * @ClassName:  HeartbeatDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月8日 下午3:40:36   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component("heartbeatDao")
public class HeartbeatDaoImpl implements HeartbeatDao{
	
	@Autowired
	AppDynamicDataCustomMapper addcDao;
	
	@Autowired
	GunLocationCustomMapper glcDao;
		
	@Autowired
	GunCustomMapper gcDao;
	
	@Autowired
	AppCustomMapper acDao;
	
	@Autowired
	AppGunCustomMapper accDao;
	
	@Autowired
	SosMessageCustomMapper sosDao;
	
	@Transactional
	public int insertAppHeartbeatSelective(WatchHeartReqMessage wHRMsg) {
		int i=0;
		
		AppCustomQueryVo appCustomQueryVo = new AppCustomQueryVo();
		AppCustom appCustom = new AppCustom();
		appCustom.setAppImei(wHRMsg.getUniqueIdentification());
		appCustomQueryVo.setAppCustom(appCustom);
		AppCustom appCustomTemp = acDao.selectAppAllColumn(appCustomQueryVo); 
		
		AppDynamicDataCustom appDynamicDataCustom = new AppDynamicDataCustom();
		appDynamicDataCustom.setAppBatteryPower(wHRMsg.getMessageBody().getAppBatteryPower());
		appDynamicDataCustom.setAppId(Integer.valueOf(appCustomTemp.getId()));
		appDynamicDataCustom.setExceptionType(Integer.valueOf(wHRMsg.getMessageBody().getExceptionCode()));
		appDynamicDataCustom.setCreateTime(StringUtil.getDate(wHRMsg.getSendTime()));
		addcDao.insertSelective(appDynamicDataCustom);
		
		ArrayList<WatchHeartReqMessageBodyGunInfo> gList = wHRMsg.getMessageBody().getGunList();
		GunCustom gunCustom = new GunCustom();
		GunLocationCustom gunLocationCustom = new GunLocationCustom();
		gunLocationCustom.setAppId(appDynamicDataCustom.getAppId());
		gunLocationCustom.setAreaCode(wHRMsg.getMessageBody().getAreaCode());
		gunLocationCustom.setCreateTime(StringUtil.getDate(wHRMsg.getSendTime()));
		gunLocationCustom.setLongitude(wHRMsg.getMessageBody().getLo());
		gunLocationCustom.setLatitude(wHRMsg.getMessageBody().getLa());
		for(WatchHeartReqMessageBodyGunInfo guninfo:gList) {
			GunCustomQueryVo gunCustomQueryVo2 = new GunCustomQueryVo();
			GunCustom gunCustom2 = new GunCustom();
			gunCustom2.setGunId(guninfo.getGunId());
			gunCustomQueryVo2.setGunCustom(gunCustom2);
			Gun gun2=gcDao.selectAllColumnByGunId(gunCustomQueryVo2);
			
			gunLocationCustom.setGunId(guninfo.getGunId());
			gunLocationCustom.setGunMac(gun2.getGunMac());
			gunLocationCustom.setGunDeviceBatteryPower(guninfo.getGunDeviceBatteryPower());
			glcDao.insertSelective(gunLocationCustom);
		
			gunCustom.setGunId(guninfo.getGunId());
			gunCustom.setRealTimeState(Integer.valueOf(guninfo.getRealTimeState()));
			gcDao.updateSelectiveByGunId(gunCustom);
			
			//20181201添加异常功能
			if(!wHRMsg.getMessageBody().getExceptionCode().equals("1")) {
				if(wHRMsg.getMessageBody().getExceptionCode().equals("8")) {
					GunCustomQueryVo gunCustomQueryVo = new GunCustomQueryVo();
					gunCustomQueryVo.setGunCustom(gunCustom);
					Gun gun=gcDao.selectAllColumnByGunId(gunCustomQueryVo);
					SosMessageCustom sosMessageCustom = new SosMessageCustom();
					sosMessageCustom.setGunImei(gun.getGunImei());
					sosMessageCustom.setGunMac(gun.getGunMac());
					sosMessageCustom.setLatitude(gunLocationCustom.getLatitude());
					sosMessageCustom.setLongitude(gunLocationCustom.getLongitude());
					sosMessageCustom.setExceptionId(Integer.valueOf(wHRMsg.getMessageBody().getExceptionCode()));
					sosDao.insertSelective(sosMessageCustom);
				}

			}
		}
		
		i=1;
		return i;
	}
	
	@Transactional
	public int intsertDeviceSelective(DeviceHeartReqMessage dhrm) {
		
		int i =0;
		
		GunCustomQueryVo gunCustomQueryVo = new GunCustomQueryVo();
		GunCustom gunCustom = new GunCustom();
		gunCustom.setGunImei(dhrm.getUniqueIdentification());
		gunCustomQueryVo.setGunCustom(gunCustom);
		
		//配合板子数据测试异常数据代码才修改，正常是必须存在对应的数据
		GunCustom gunCustomTemp = null;
		gunCustomTemp = gcDao.selectAllColumnByGunImei(gunCustomQueryVo);		
		if(gunCustomTemp ==null) {
			gunCustomTemp = new GunCustom();
			gunCustom.setGunId("");
			gunCustom.setGunMac("");
		}
		
		AppGunCustomQueryVo appGunCustomQueryVo = new AppGunCustomQueryVo();
		AppGunCustom appGunCustom = new AppGunCustom();
		appGunCustom.setGunId(gunCustomTemp.getGunId());
		appGunCustom.setAllotState(Integer.valueOf(StringConstant.GUN_ALLOTED_STATE));
		appGunCustomQueryVo.setAppGunCustom(appGunCustom);

		//配合板子数据测试异常数据代码才修改，正常是必须存在对应的数据
		AppGunCustom appGunCustomTemp =null;
		
		try {
			appGunCustomTemp=accDao.selectAllColumn(appGunCustomQueryVo);	
		} catch (Exception e) {
			if(null==appGunCustomTemp) {
				appGunCustomTemp=new AppGunCustom();
				appGunCustomTemp.setAppId(Integer.valueOf(0));
			}
		}

		
		GunLocationCustom gunLocationCustom = new GunLocationCustom();
		gunLocationCustom.setAppId(appGunCustomTemp.getAppId());
		gunLocationCustom.setGunId(gunCustomTemp.getGunId());
		gunLocationCustom.setGunMac(dhrm.getUniqueIdentification());
		gunLocationCustom.setAreaCode(dhrm.getMessageBody().getAreaCode());
		gunLocationCustom.setDirector(dhrm.getMessageBody().getDirector());
		gunLocationCustom.setGunDeviceBatteryPower(dhrm.getMessageBody().getGunDeviceBatteryPower());
		gunLocationCustom.setGunDeviceState(Integer.valueOf(dhrm.getMessageBody().getInPosition()));
		gunLocationCustom.setCreateTime(StringUtil.getDate(dhrm.getSendTime()));
		double[] temp= CoordinateConvertUtil.wgs2BD09(Double.valueOf(dhrm.getMessageBody().getLa())
				,Double.valueOf(dhrm.getMessageBody().getLo()));	
		String lo=String.format("%.6f", temp[1]);
		String la=String.format("%.6f", temp[0]);
		gunLocationCustom.setLongitude(lo);
		gunLocationCustom.setLatitude(la);
		
/*		gunLocationCustom.setLongitude(dhrm.getMessageBody().getLo());
		gunLocationCustom.setLatitude(dhrm.getMessageBody().getLa());*/
		glcDao.insertSelective(gunLocationCustom);
		
		return 1;
	}
	
}
