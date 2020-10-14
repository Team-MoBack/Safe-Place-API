package com.moBack.backend.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moBack.backend.entity.TestUser;

public interface TestUserService extends UserDetailsService {
 //   Collection<GrantedAuthority> getAuthorities(String username);
    public TestUser readUser(String username);
    public void createUser(TestUser user);
    public void deleteUser(String username);
    public PasswordEncoder passwordEncoder();
}