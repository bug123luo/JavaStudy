package com.tct.db.mapper;

import com.tct.db.po.AppGunUser;
import com.tct.db.po.AppGunUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppGunUserMapper {
    long countByExample(AppGunUserExample example);

    int deleteByExample(AppGunUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppGunUser record);

    int insertSelective(AppGunUser record);

    List<AppGunUser> selectByExample(AppGunUserExample example);

    AppGunUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppGunUser record, @Param("example") AppGunUserExample example);

    int updateByExample(@Param("record") AppGunUser record, @Param("example") AppGunUserExample example);

    int updateByPrimaryKeySelective(AppGunUser record);

    int updateByPrimaryKey(AppGunUser record);
}