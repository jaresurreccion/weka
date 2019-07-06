package com.tfg.wekaWeb.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfg.wekaWeb.dto.User;
import com.tfg.wekaWeb.service.UserService;

//@RestController
@Controller

public class loginController {
	   private static final Logger logger = LoggerFactory.getLogger(loginController.class);



		@Autowired
		private UserService userService;

		
		@RequestMapping(value="/",method = RequestMethod.GET)
		public String login(Model model) {		
			return "index";
		}
		
		@RequestMapping(value="/nuevoUsuario",method = RequestMethod.GET)
		public String nuevoUsuario(Model model) {		
			return "nuevoUsuario";
		}
		
		@RequestMapping(value="/home", method = RequestMethod.POST)
		public String loginOK(String user,String pass,ModelMap model,HttpServletResponse response) throws Exception  {
			Boolean login = userService.login(user, pass);
			if(login ) {
				User e = userService.buscarPorUsuario(user);
				Cookie cookie = new Cookie("username",e.getUsername());
				response.addCookie(cookie);
				return "home";
			}
			else {				
				return "index";
			}
		}
		
		@RequestMapping(value="/createUsuario", method = RequestMethod.POST)
		public String createUsuario(String nombre,String apellido1,String apellido2,
			String username,String pass,ModelMap model,HttpServletResponse response) throws Exception  {
				User e = new User(apellido1, apellido2, nombre, username, pass, 1, new Date(), new Date(), new Date());
				e = userService.saveUser(e);
				if(e!= null) {
					Cookie cookie = new Cookie("username",e.getUsername());
					response.addCookie(cookie);
					return "home";
				}else {
					return "index";
				}
				
				
			
							
				
			
		}
		
		@RequestMapping(value="/algoritmos",method = RequestMethod.GET)
		public String Algoritmos(ModelMap model) {
			return "algoritmos";
		}
		@RequestMapping(value="/ficheros",method = RequestMethod.GET)
		public String Ficheros(ModelMap model) {
			return "ficheros";
		}
		
		@RequestMapping(value="/perfil",method = RequestMethod.GET)
		public String MiPerfil(ModelMap model,HttpServletRequest request, @CookieValue(value = "username", defaultValue = "Atta") String username) throws Exception {
			User e = userService.buscarPorUsuario(username);
			
			model.addAttribute("nombre",e.getNombre());
			model.addAttribute("apellido1",e.getPrimerApellido());
			model.addAttribute("apellido2",e.getSegundoApellido());
			model.addAttribute("activo",e.getFlag());
			model.addAttribute("id",e.getId());
			
			return "perfil";
		}
		
		@RequestMapping(value="/home",method = RequestMethod.GET)
		public String miHome(ModelMap model) {
			return "home";
		}
		
		@RequestMapping(value="/updateUser/{id}", method = RequestMethod.POST)
		public String updateUserService(String id,String nombre,String apellido1,String apellido2,ModelMap model,HttpServletRequest request) throws Exception  {
			User e = userService.findById(Integer.parseInt(id));
			e.setNombre(nombre);
			e.setPrimerApellido(apellido1);
			e.setSegundoApellido(apellido2);
			e = userService.updateUser(e);
			model.addAttribute("nombre",e.getNombre());
			model.addAttribute("apellido1",e.getPrimerApellido());
			model.addAttribute("apellido2",e.getSegundoApellido());
			model.addAttribute("activo",e.getFlag());
			model.addAttribute("id",e.getId());
			return "redirect:/perfil";
		}
		
		
		
		
		
}
