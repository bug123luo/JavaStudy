package com.tct.db.mapper;

import com.tct.db.po.MessageRecords;
import com.tct.db.po.MessageRecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageRecordsMapper {
    long countByExample(MessageRecordsExample example);

    int deleteByExample(MessageRecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageRecords record);

    int insertSelective(MessageRecords record);

    List<MessageRecords> selectByExample(MessageRecordsExample example);

    MessageRecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageRecords record, @Param("example") MessageRecordsExample example);

    int updateByExample(@Param("record") MessageRecords record, @Param("example") MessageRecordsExample example);

    int updateByPrimaryKeySelective(MessageRecords record);

    int updateByPrimaryKey(MessageRecords record);
}