package com.thinkonweb.springmvc.web.controller;

import static com.thinkonweb.springmvc.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static com.thinkonweb.springmvc.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkonweb.springmvc.domain.Level;
import com.thinkonweb.springmvc.domain.User;
import com.thinkonweb.springmvc.service.UserService;
import com.thinkonweb.springmvc.service.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/serviceTest", method = RequestMethod.GET)
	public String serviceTest(Locale locale, Model model) {
		List<User> users = Arrays.asList(
								new User("bumjin", 		"박범진", "p1", "user1@ksug.org", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0), //49, 0
								new User("joytouch", 	"강명성", "p2", "user2@ksug.org", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0), //50, 0
								new User("erwins", 		"신승한", "p3", "user3@ksug.org", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD - 1), //60, 29
								new User("madnite1", 	"이상호", "p4", "user4@ksug.org", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD), //60, 30
								new User("green", 		"오민규", "p5", "user5@ksug.org", Level.GOLD, 100, Integer.MAX_VALUE)
							);
		
		this.userService.deleteAll();
		
		for (int i = 0; i < users.size(); i++) {
			this.userService.add(users.get(i));
		}
		
		List<User> users2 = this.userService.getAll();
		model.addAttribute("users", users2);
		
		return "userServiceTest";
	}
}
