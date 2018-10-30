package com.tct.db.mapper;

import com.tct.db.po.GunUser;
import com.tct.db.po.GunUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GunUserMapper {
    long countByExample(GunUserExample example);

    int deleteByExample(GunUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(GunUser record);

    int insertSelective(GunUser record);

    List<GunUser> selectByExample(GunUserExample example);

    GunUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") GunUser record, @Param("example") GunUserExample example);

    int updateByExample(@Param("record") GunUser record, @Param("example") GunUserExample example);

    int updateByPrimaryKeySelective(GunUser record);

    int updateByPrimaryKey(GunUser record);
}