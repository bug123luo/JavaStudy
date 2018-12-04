package com.tct.db.mapper;

import com.tct.db.po.SosMessageCustom;

public interface SosMessageCustomMapper {
	int insertSelective(SosMessageCustom sosMessageCustom);
	
	int updateSelectiveByGunMac(SosMessageCustom sosMessageCustom);
}
