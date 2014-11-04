package com.thinkonweb.springmvc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thinkonweb.springmvc.domain.User;

public interface UserService {
	void add(User user);
	void deleteAll();
	void update(User user);	
	void upgradeLevels();
	
	User get(String id);

	@Transactional(readOnly=true)
	List<User> getAll();
}
