package com.tfg.wekaWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.tfg.wekaWeb.service.UserService;

//@RestController
@Controller

public class loginController {
	   private static final Logger logger = LoggerFactory.getLogger(loginController.class);



		@Autowired
		private UserService userService;

		
		@RequestMapping(value="/",method = RequestMethod.GET)
		public String login(Model model) {
			model.addAttribute("users", userService.buscarUsuarios());
			return "index";
		}
		
		@RequestMapping(value="/home", method = RequestMethod.POST)
		public String loginOK(String user,String pass,ModelMap model) throws Exception  {
			Boolean login = userService.login(user, pass);
			if(login ) {
				model.addAttribute("Hello","NIENNNNNNNN");
				return "home";
			}
			else {
				
				return "index";
			}
			
			
		}
		
		
}
