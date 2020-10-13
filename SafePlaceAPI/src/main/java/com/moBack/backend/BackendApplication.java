package com.moBack.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.moBack.backend.config.SecurityConfig;

@SpringBootApplication
public class BackendApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
