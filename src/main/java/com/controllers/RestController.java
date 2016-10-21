package com.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Services.UserService;
import com.entities.user;
import com.repositories.UserDAO;

@Controller
public class RestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/rest", method= RequestMethod.GET)
	@ResponseBody
	public user Restcontroller(){
		user USER = new user();
		USER.setEmail("My test");
		USER.setId(1);
		return USER;
	}
	
	@RequestMapping(value="/restMap", method= RequestMethod.GET)
	@ResponseBody
	//public String  RestMapcontroller(){ return view
	//public Map<String, Object> RestMapcontroller(){ return only body
	public ResponseEntity<Map<String, Object>> RestMapcontroller(){ // return header, body
		user USER = new user();
		USER.setEmail("My test");
		USER.setId(1);
		
		user USER2 = new user();
		USER2.setEmail("My test2");
		USER2.setId(2);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("restMap", USER);
		model.put("restMap2", USER2);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK); //responseentity
		//return model; body
		//return USER; view
	}
	
	@RequestMapping(value="restUser", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> RestUsercontroller(){
		Map<String, Object> model = new HashMap<String, Object>();
		//System.out.println("1");
		model.put("Users", userService.listUser());
		//System.out.println("2");
		return new ResponseEntity<Map<String, Object>> (model,HttpStatus.OK);
	}

	@RequestMapping(value="deleteUser", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> deleteUser(){
		Map<String, Object> model = new HashMap<String, Object>();
	//	System.out.println("1");
		model.put("Users", userDAO.deleteUser());
	//	System.out.println("2");
		return new ResponseEntity<Map<String, Object>> (model,HttpStatus.OK);
	}
}
