package com.thinkonweb.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkonweb.springmvc.dao.UserDao;
import com.thinkonweb.springmvc.domain.Level;
import com.thinkonweb.springmvc.domain.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;

	private UserDao userDao;
	private MailSender mailSender;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void add(User user) {
		if (user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}

	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for (User user : users) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel(); 
		switch(currentLevel) {                                   
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER); 
			case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level: " + currentLevel); 
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEMail(user);
	}
	
	private void sendUpgradeEMail(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("useradmin@ksug.org");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());
		
		this.mailSender.send(mailMessage);
	}

	@Override
	@Transactional(readOnly=true)
	public User get(String id) {
		return this.userDao.get(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> getAll() {
		return this.userDao.getAll();
	}

	@Override
	public void deleteAll() {
		this.userDao.deleteAll();
	}

	@Override
	public void update(User user) {
		this.userDao.update(user);
	}
}

