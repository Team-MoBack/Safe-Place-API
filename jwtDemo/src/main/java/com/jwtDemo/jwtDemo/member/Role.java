package com.jwtDemo.jwtDemo.member;


public enum Role {

	USER("ROLE_USER"),ADMIN("ROLE_ADMIN");
	private String value;

	private Role(String value) { 
		this.value = value; 
	} 
	public String getValue() {
		return value;
	}
}


