package com.moBack.backend.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.BackendApplication;
import com.moBack.backend.entity.TestUser;
import com.moBack.backend.service.TestUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {BackendApplication.class})
public class UserServiceTest {
     @Autowired private TestUserService userService;
    
     private TestUser user1;
    
     @Before
     public void setup() {
    	 System.out.println(">>>>");
          user1 = new TestUser();
          user1.setUsername("user1");
          user1.setPassword("pass1");
          user1.setAccountNonExpired(true);
          user1.setAccountNonLocked(true);
          user1.setName("USER1");
          user1.setCredentialsNonExpired(true);
          user1.setEnabled(true);
          user1.setAuthorities(AuthorityUtils.createAuthorityList("USER","ADMIN"));
     }
    
     @Test
     public void createUserTest() {
          userService.deleteUser(user1.getUsername());
          userService.createUser(user1);
          TestUser user = userService.readUser(user1.getUsername());
          assertThat(user.getUsername(), is(user1.getUsername()));
         
          PasswordEncoder passwordEncoder = userService.passwordEncoder();
          assertThat(passwordEncoder.matches("pass1", user.getPassword()), is(true));

          Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
          Iterator<? extends GrantedAuthority> it = authorities1.iterator();
          Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
          while (it.hasNext()) {
               GrantedAuthority authority = it.next();
               assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
          }
     }
}