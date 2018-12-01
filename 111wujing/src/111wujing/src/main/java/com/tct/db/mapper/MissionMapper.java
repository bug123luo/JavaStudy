package com.tct.db.mapper;

import com.tct.db.po.Mission;
import com.tct.db.po.MissionExample;
import com.tct.db.po.MissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MissionMapper {
    long countByExample(MissionExample example);

    int deleteByExample(MissionExample example);

    int deleteByPrimaryKey(MissionKey key);

    int insert(Mission record);

    int insertSelective(Mission record);

    List<Mission> selectByExample(MissionExample example);

    Mission selectByPrimaryKey(MissionKey key);

    int updateByExampleSelective(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByExample(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);
}