package com.tct.db.mapper;

import com.tct.db.po.ThirdServerUser;
import com.tct.db.po.ThirdServerUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThirdServerUserMapper {
    long countByExample(ThirdServerUserExample example);

    int deleteByExample(ThirdServerUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ThirdServerUser record);

    int insertSelective(ThirdServerUser record);

    List<ThirdServerUser> selectByExample(ThirdServerUserExample example);

    ThirdServerUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ThirdServerUser record, @Param("example") ThirdServerUserExample example);

    int updateByExample(@Param("record") ThirdServerUser record, @Param("example") ThirdServerUserExample example);

    int updateByPrimaryKeySelective(ThirdServerUser record);

    int updateByPrimaryKey(ThirdServerUser record);
}