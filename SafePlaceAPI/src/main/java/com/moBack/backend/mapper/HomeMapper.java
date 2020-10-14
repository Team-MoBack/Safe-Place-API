package com.moBack.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.moBack.backend.entity.Home;

@Mapper
public interface HomeMapper {
       
       public Home readHome(@Param("name") String name);
}
