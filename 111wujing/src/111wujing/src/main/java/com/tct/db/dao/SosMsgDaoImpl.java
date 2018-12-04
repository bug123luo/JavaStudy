package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.tct.db.mapper.SosMessageCustomMapper;
import com.tct.db.po.SosMessageCustom;

public class SosMsgDaoImpl implements SosMsgDao {

	@Autowired
	SosMessageCustomMapper sosDao;
	
	/**   
	 * <p>Title: updateSelectiveByGunMac</p>   
	 * <p>Description: </p>   
	 * @param gunMac
	 * @return   
	 * @see com.tct.db.dao.SosMsgDao#updateSelectiveByGunMac(java.lang.String)   
	 */
	@Override
	public int updateSelectiveByGunMac(String gunMac) {
		
		SosMessageCustom sosMessageCustom = new SosMessageCustom();
		sosMessageCustom.setGunMac(gunMac);
		sosMessageCustom.setState(0);
		sosDao.updateSelectiveByGunMac(sosMessageCustom);
		return 0;
	}

}
