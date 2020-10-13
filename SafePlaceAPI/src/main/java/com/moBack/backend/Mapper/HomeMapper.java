package com.moBack.backend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.moBack.backend.entity.Home;

@Mapper
public interface HomeMapper {
       @Select("SELECT * FROM home WHERE name = #{name}")
       public Home readHome(@Param("name") String name);
}
