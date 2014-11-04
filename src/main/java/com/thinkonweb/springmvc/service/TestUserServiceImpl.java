package com.thinkonweb.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkonweb.springmvc.domain.User;
import com.thinkonweb.springmvc.exception.TestUserServiceException;

@Service("testUserService")
@Transactional
public class TestUserServiceImpl extends UserServiceImpl {
	private String id = "madnite1";
	
	@Transactional(readOnly=true)
	public List<User> getAll() {
		for (User user : super.getAll()) {
			super.update(user);
		}
		return null;
	}
	
	protected void upgradeLevel(User user) {
		if (user.getId().equals(this.id)) throw new TestUserServiceException();  
		super.upgradeLevel(user);  
	}
}

