package com.tct.db.mapper;

import com.tct.db.po.GunDynamicData;
import com.tct.db.po.GunDynamicDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GunDynamicDataMapper {
    long countByExample(GunDynamicDataExample example);

    int deleteByExample(GunDynamicDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GunDynamicData record);

    int insertSelective(GunDynamicData record);

    List<GunDynamicData> selectByExample(GunDynamicDataExample example);

    GunDynamicData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GunDynamicData record, @Param("example") GunDynamicDataExample example);

    int updateByExample(@Param("record") GunDynamicData record, @Param("example") GunDynamicDataExample example);

    int updateByPrimaryKeySelective(GunDynamicData record);

    int updateByPrimaryKey(GunDynamicData record);
}