package com.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entities.user;
import com.repositories.UserDAO;

@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String users(Model model){
		model.addAttribute("Message", "Bora");
		return "users";
	}
	
	@RequestMapping(value="user", method = RequestMethod.GET)
	public ModelAndView modelAndView(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Message", "this message from model and view");
		return new ModelAndView("users", model);
	}
	
	@RequestMapping(value="/users", method= RequestMethod.POST)
	public String addNewUser(user User, Model model){
		if(userDAO.addNewUser(User)){
			model.addAttribute("Message","SUCCESS");
		}else
			model.addAttribute("Message","failed");
		return "success";
	}
}
