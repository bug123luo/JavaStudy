package com.lcclovehww.mapper;

import com.lcclovehww.pojo.Role;

public interface RoleMapper {
	public Role getRole(Long id);
	public int deleteRole(Long id);
	public int insertRole(Role role);
}
