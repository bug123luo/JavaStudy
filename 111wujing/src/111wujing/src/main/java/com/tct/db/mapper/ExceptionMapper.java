package com.tct.db.mapper;

import com.tct.db.po.Exception;
import com.tct.db.po.ExceptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExceptionMapper {
    long countByExample(ExceptionExample example);

    int deleteByExample(ExceptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exception record);

    int insertSelective(Exception record);

    List<Exception> selectByExample(ExceptionExample example);

    Exception selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Exception record, @Param("example") ExceptionExample example);

    int updateByExample(@Param("record") Exception record, @Param("example") ExceptionExample example);

    int updateByPrimaryKeySelective(Exception record);

    int updateByPrimaryKey(Exception record);
}