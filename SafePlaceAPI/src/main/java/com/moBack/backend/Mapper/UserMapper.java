package com.moBack.backend.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.moBack.backend.entity.TestUser;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user WHERE username = #{username}")
     public TestUser readUser(@Param("username") String username);
	@Select("SELECT authority_name FROM authority WHERE username = #{username}")
     public List<String> readAuthority(@Param("username") String username);
}