package com.tct.db.mapper;

import com.tct.db.po.GunBulletCount;
import com.tct.db.po.GunBulletCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GunBulletCountMapper {
    long countByExample(GunBulletCountExample example);

    int deleteByExample(GunBulletCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GunBulletCount record);

    int insertSelective(GunBulletCount record);

    List<GunBulletCount> selectByExample(GunBulletCountExample example);

    GunBulletCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GunBulletCount record, @Param("example") GunBulletCountExample example);

    int updateByExample(@Param("record") GunBulletCount record, @Param("example") GunBulletCountExample example);

    int updateByPrimaryKeySelective(GunBulletCount record);

    int updateByPrimaryKey(GunBulletCount record);
}