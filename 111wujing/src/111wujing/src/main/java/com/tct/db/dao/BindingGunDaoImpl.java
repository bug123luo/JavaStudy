/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BindingGunDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月7日 上午11:26:15   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.tct.codec.protocol.pojo.BindingReqMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBodyGunInfo;
import com.tct.codec.protocol.pojo.BindingResMessage;
import com.tct.db.mapper.AppCustomMapper;
import com.tct.db.mapper.AppGunCustomMapper;
import com.tct.db.po.AppCustom;
import com.tct.db.po.AppCustomQueryVo;
import com.tct.db.po.AppGunCustom;
import com.tct.util.StringConstant;
import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  BindingGunDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月7日 上午11:26:15   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component
public class BindingGunDaoImpl implements BindingGunDao{

	@Autowired
	AppGunCustomMapper appGunDao;
		
	@Autowired
	AppCustomMapper appDao;
	
	public int insertSelective(AppGunCustom appGunCustom) {
		int i = 0;
		i = appGunDao.insertSelective(appGunCustom);
		return i;
	}
	
	public int updateSelectiveByAppId(AppGunCustom appGunCustom) {
		int i = 0;
		i =  appGunDao.updateSelectiveByAppId(appGunCustom);
		return i;
	}
	
	@Transactional
	public void insertGugList(BindingReqMessage msg) {
		
		String imei = msg.getUniqueIdentification();
		AppCustomQueryVo appCustomQueryVo = new AppCustomQueryVo();
		AppCustom appCustom = new AppCustom();
		appCustom.setAppImei(imei);
		appCustomQueryVo.setAppCustom(appCustom);
		AppCustom appCustomTemplate=appDao.selectAppAllColumn(appCustomQueryVo);
		ArrayList<BindingReqMessageBodyGunInfo> gunList = msg.getMessageBody().getGunList();
		AppGunCustom appGunCustom = new AppGunCustom();
		
		appGunCustom.setAppId(Integer.valueOf(appCustomTemplate.getId()));
		appGunCustom.setAllotState(Integer.valueOf(StringConstant.GUN_ALLOTING_STATE));
		for(BindingReqMessageBodyGunInfo guninfo:gunList) {
			appGunCustom.setGunId(guninfo.getGunId());
			appGunDao.insertSelective(appGunCustom);
		}
	}
	
	@Transactional
	public void updateGunState(BindingResMessage bResMsg) {
		
		String state = bResMsg.getMessageBody().getState();
		String imei = bResMsg.getUniqueIdentification();
		
		AppCustomQueryVo appCustomQueryVo = new AppCustomQueryVo();
		AppCustom appCustom = new AppCustom();
		appCustom.setAppImei(imei);
		appCustomQueryVo.setAppCustom(appCustom);
		AppCustom appCustomTemp =appDao.selectAppAllColumn(appCustomQueryVo);

		AppGunCustom appGunCustom = new AppGunCustom();
		if(state.equals(StringConstant.SUCCESS_OLD_STATE)) {
			appGunCustom.setAllotState(Integer.valueOf(StringConstant.GUN_ALLOTED_STATE));	
		}else {
			appGunCustom.setAllotState(Integer.valueOf(StringConstant.GUN_UNALLOT_STATE));
		}
		appGunCustom.setAppId(appCustomTemp.getId());
		appGunDao.updateSelectiveByAppIdAndState(appGunCustom);
	}
}
