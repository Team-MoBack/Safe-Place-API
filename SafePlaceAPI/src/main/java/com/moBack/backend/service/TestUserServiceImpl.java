package com.moBack.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moBack.backend.Mapper.UserMapper;
import com.moBack.backend.entity.TestUser;

@Service
public class TestUserServiceImpl implements TestUserService {
    
     @Autowired UserMapper userMapper;

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          TestUser user = userMapper.readUser(username);
          user.setAuthorities(getAuthorities(username));
         
          return user;
     }
    
     public Collection<GrantedAuthority> getAuthorities(String username) {
    	 	System.out.println(">>>>>>>>>>"+username);
          List<String> string_authorities = userMapper.readAuthority(username);
          List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
          for (String authority : string_authorities) {
               authorities.add(new SimpleGrantedAuthority(authority));
          }
          return authorities;
     }
}