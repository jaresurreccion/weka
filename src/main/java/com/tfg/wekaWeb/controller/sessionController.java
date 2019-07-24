package com.tfg.wekaWeb.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfg.wekaWeb.dto.SesionTrabajo;
import com.tfg.wekaWeb.dto.User;
import com.tfg.wekaWeb.service.SesionTrabajoService;

@Controller
public class sessionController {
	 private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	   
	HttpSession session;
	   
	@Autowired
	private SesionTrabajoService trabajosService;
	
	@RequestMapping(value="/crearSesion", method = RequestMethod.POST)
	public String createUsuario(String nombre,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception  {
			session = request.getSession(true);
			Integer idUser = Integer.parseInt(session.getAttribute("idUser").toString());
			trabajosService.nuevaSesionTrabajo(idUser, nombre);
			session.setAttribute("trabajo", trabajosService.buscarSesiones(idUser));
			
				return "home";
			

	}
}
