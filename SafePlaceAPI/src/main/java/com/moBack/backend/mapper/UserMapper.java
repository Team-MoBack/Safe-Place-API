package com.moBack.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.moBack.backend.entity.TestUser;

@Mapper
public interface UserMapper {
	public TestUser readUser(String username);
	public List<String> readAuthority(String username);
	public void createUser(TestUser user);
	public void createAuthority(TestUser user);
	public void deleteUser(String username);
	public void deleteAuthority(String username);
}