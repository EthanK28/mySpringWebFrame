package com.thinkonweb.springmvc.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkonweb.springmvc.domain.User;
import com.thinkonweb.springmvc.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	@Qualifier("userService")
	UserServiceImpl userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegistration(Model model) {
        User userForm = new User();    
        model.addAttribute("userForm", userForm);
         
        List<String> professionList = new ArrayList<String>();
        professionList.add("Developer");
        professionList.add("Designer");
        professionList.add("IT Manager");
        model.addAttribute("professionList", professionList);
         
        return "admin/registration";
    }
     
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user, Model model) {
         
        this.userService.add(user);
         
        // for testing purpose:
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("profession: " + user.getProfession());
         
        return "admin/registrationSuccess";
    }
    
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String allUsers(Locale locale, Model model) {
		List<User> users = this.userService.getAll();
		model.addAttribute("users", users);
		
		return "admin/allUsers";
	}
}
