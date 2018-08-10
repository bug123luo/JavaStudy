package com.lcclovehww.mapper;

import java.util.List;
import java.util.Map;

import com.lcclovehww.po.Person;

public interface PersonCustomMapper {
	List<Person> selectByPersonQueryVo(Map map) throws Exception;
}