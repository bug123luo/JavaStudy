/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  GunBulletDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月12日 下午3:21:31   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tct.codec.protocol.pojo.GetBulletNumberResMessage;
import com.tct.codec.protocol.pojo.ReportBulletNumberReqMessage;
import com.tct.db.mapper.GunBulletCountCustomMapper;
import com.tct.db.mapper.GunCustomMapper;
import com.tct.db.po.GunBulletCountCustom;
import com.tct.db.po.GunCustom;
import com.tct.util.StringConstant;
import com.tct.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  GunBulletDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月12日 下午3:21:31   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */


@Slf4j
@Component("gunBulletDao")
public class GunBulletDaoImpl  implements GunBulletDao{
	
	@Autowired
	GunCustomMapper gcDao;
	
	@Autowired
	GunBulletCountCustomMapper gBCDao;
	
	public int insertSelective(ReportBulletNumberReqMessage msg) {
		GunBulletCountCustom gunBulletCountCustom = new GunBulletCountCustom();
		gunBulletCountCustom.setBulletNumber(Integer.valueOf(msg.getMessageBody().getBullet_number()));
		gunBulletCountCustom.setCreateTime(StringUtil.getDate(msg.getMessageBody().getTime()));
		gunBulletCountCustom.setGunId(msg.getMessageBody().getGunId());
		gunBulletCountCustom.setLa(msg.getMessageBody().getLa());
		gunBulletCountCustom.setLo(msg.getMessageBody().getLo());
		
		int i=0;
		i=gBCDao.insertSelective(gunBulletCountCustom);
		return i;
	}
	
	public int updateTotalCount(GetBulletNumberResMessage gbnrMsg) {
		GunCustom gunCustom = new GunCustom();
		gunCustom.setTotalBulletNumber(Integer.valueOf(gbnrMsg.getMessageBody().getTotalBulletNumber()));
		gcDao.updateByGunIdAndCount(gunCustom);
		return gcDao.updateByGunIdAndCount(gunCustom);
	}
}
