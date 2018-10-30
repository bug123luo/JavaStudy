package com.tct.db.mapper;

import com.tct.db.po.GunLocation;
import com.tct.db.po.GunLocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GunLocationMapper {
    long countByExample(GunLocationExample example);

    int deleteByExample(GunLocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GunLocation record);

    int insertSelective(GunLocation record);

    List<GunLocation> selectByExample(GunLocationExample example);

    GunLocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GunLocation record, @Param("example") GunLocationExample example);

    int updateByExample(@Param("record") GunLocation record, @Param("example") GunLocationExample example);

    int updateByPrimaryKeySelective(GunLocation record);

    int updateByPrimaryKey(GunLocation record);
}