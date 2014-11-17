package com.thinkonweb.springmvc.dao;

import com.thinkonweb.springmvc.domain.UserRole;

public interface UserRoleDao {
	void add(UserRole userRole);
	UserRole get(String userId);
	void update(UserRole userRole);
}