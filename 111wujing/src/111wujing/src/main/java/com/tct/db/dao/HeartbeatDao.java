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

import com.tct.codec.protocol.pojo.WatchHeartReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartReqMessageBody.Guninfo;
import com.tct.db.mapper.AppCustomMapper;
import com.tct.db.mapper.AppDynamicDataCustomMapper;

import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.mapper.GunLocationCustomMapper;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;
import com.tct.db.po.AppDynamicDataCustom;
import com.tct.db.po.GunCustom;
import com.tct.db.po.GunLocationCustom;
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
@Component
public class HeartbeatDao {
	
	@Autowired
	AppDynamicDataCustomMapper addcDao;
	
	@Autowired
	GunLocationCustomMapper glcDao;
		
	@Autowired
	GunCustomMapper gcDao;
	
	@Autowired
	AppCustomMapper acDao;
	
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
		
		ArrayList<Guninfo> gList = wHRMsg.getMessageBody().getGunList();
		GunCustom gunCustom = new GunCustom();
		GunLocationCustom gunLocationCustom = new GunLocationCustom();
		gunLocationCustom.setAppId(appDynamicDataCustom.getAppId());
		gunLocationCustom.setAreaCode(wHRMsg.getMessageBody().getAreaCode());
		gunLocationCustom.setCreateTime(StringUtil.getDate(wHRMsg.getSendTime()));
		gunLocationCustom.setLongitude(wHRMsg.getMessageBody().getLo());
		gunLocationCustom.setLatitude(wHRMsg.getMessageBody().getLa());
		for(Guninfo guninfo:gList) {
			gunLocationCustom.setGunId(guninfo.getGunId());
			gunLocationCustom.setGunDeviceBatteryPower(guninfo.getGunDeviceBatteryPower());
			glcDao.insertSelective(gunLocationCustom);
			
			gunCustom.setRealTimeState(Integer.valueOf(guninfo.getRealTimeState()));
			gcDao.updateByGunId(gunCustom);
		}
		
		i=1;
		return i;
	}
	
}
