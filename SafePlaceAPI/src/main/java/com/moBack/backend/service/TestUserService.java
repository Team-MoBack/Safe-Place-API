package com.moBack.backend.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TestUserService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String username);
}
