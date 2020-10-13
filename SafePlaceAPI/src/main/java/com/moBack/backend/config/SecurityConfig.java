package com.moBack.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER").and().withUser("admin")
        .password("admin").roles("ADMIN");
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	  //    http.authorizeRequests().antMatchers("/rest/**").authenticated();
//    http.csrf().disable();
//    http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        
  }
}