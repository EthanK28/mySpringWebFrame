package com.thinkonweb.springmvc.domain;

public class UserRole {
	int id;
	String userId;
	String role;
	
	public UserRole() {
	}
	
	public UserRole(String userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
