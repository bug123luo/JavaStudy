package com.lcclovehww.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lcclovehww.pojo.Role;
import com.lcclovehww.pojo.RoleParams;

public interface RoleMapper {
	public Role getRole(Long id);
	public int deleteRole(Long id);
	public int insertRole(Role role);
	public List<Role> findRoleByMap(Map<String, String> params);
	public List<Role> findRoleByAnnotation(@Param("roleName") String rolename,@Param("remark")String remark);
	public List<Role> findRoleByParms(RoleParams params);
	public int insertRoleGetKey(Role role);
}
