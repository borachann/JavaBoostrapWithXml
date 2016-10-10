package controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Services.UserService;
import Services.UserServiceInf;
import entities.user;

@Controller
public class RestController {
	
	@Autowired
	UserServiceInf userService;

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
		model.put("Users", userService.listUser());
		
		return new ResponseEntity<Map<String, Object>> (model,HttpStatus.OK);
	}
}
