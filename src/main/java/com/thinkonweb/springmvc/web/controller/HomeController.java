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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
 	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This is default page!");
	  model.setViewName("home");
	  return model;
 
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
	  System.out.println("!!!!!");
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");
 
	  return model;
 
	}
 
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
 
	  ModelAndView model = new ModelAndView();
 
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
 
	  model.setViewName("403");
	  return model;
 	}
}
