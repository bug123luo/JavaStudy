package com.tct.db.mapper;

import com.tct.db.po.WebUserLogin;
import com.tct.db.po.WebUserLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebUserLoginMapper {
    long countByExample(WebUserLoginExample example);

    int deleteByExample(WebUserLoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WebUserLogin record);

    int insertSelective(WebUserLogin record);

    List<WebUserLogin> selectByExample(WebUserLoginExample example);

    WebUserLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WebUserLogin record, @Param("example") WebUserLoginExample example);

    int updateByExample(@Param("record") WebUserLogin record, @Param("example") WebUserLoginExample example);

    int updateByPrimaryKeySelective(WebUserLogin record);

    int updateByPrimaryKey(WebUserLogin record);
}