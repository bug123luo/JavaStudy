package com.tct.db.mapper;

import com.tct.db.po.AppDynamicData;
import com.tct.db.po.AppDynamicDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppDynamicDataMapper {
    long countByExample(AppDynamicDataExample example);

    int deleteByExample(AppDynamicDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppDynamicData record);

    int insertSelective(AppDynamicData record);

    List<AppDynamicData> selectByExample(AppDynamicDataExample example);

    AppDynamicData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppDynamicData record, @Param("example") AppDynamicDataExample example);

    int updateByExample(@Param("record") AppDynamicData record, @Param("example") AppDynamicDataExample example);

    int updateByPrimaryKeySelective(AppDynamicData record);

    int updateByPrimaryKey(AppDynamicData record);
}