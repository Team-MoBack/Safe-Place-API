package com.moBack.springboot.moBack.Model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class MoBackUser {

	//@NotNull(message="is required")
	//@Size(min=1, message="is required")	
	private String userName;

	//@NotNull(message="is required")
	//@Size(min=1, message="is required")
	private String password;

	public MoBackUser() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
