package com.tfg.wekaWeb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.SesionTrabajo;
import com.tfg.wekaWeb.dto.User;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UtilsService;

@Controller
public class sessionController {
	 private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	   
	HttpSession session;
	private UtilsService utils = new UtilsService();
	   
	@Autowired
	private SesionTrabajoService trabajosService;
	
	@RequestMapping(value="/crearSesion", method = RequestMethod.POST)
	public String createUsuario(String nombre,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception  {
			session = utils.isValidSession(request);
			Integer idUser = Integer.parseInt(session.getAttribute("idUser").toString());
			trabajosService.nuevaSesionTrabajo(idUser, nombre);
			session.setAttribute("trabajo", trabajosService.buscarSesiones(idUser));
			
				return "home";
	}
	
	@RequestMapping(value="/activateSession/{idSession}",method = RequestMethod.GET)
	public String activarSesion(ModelMap model, @CookieValue(value = "userId", defaultValue = "Attat") String userId,HttpServletRequest request,
			@PathVariable String idSession) {
		session = utils.isValidSession(request);
		SesionTrabajo  actual = trabajosService.buscarXid(Integer.parseInt(idSession));
		session.setAttribute("sesionActiva", true);
		session.setAttribute("sesionActivaNombre", actual.getNombre());
		session.setAttribute("sesionActivaIdSesion",actual.getIdSesion());
		session.setAttribute("sesionActivaIdFile", actual.getIdFile());
		session.setAttribute("sesionActivaIdAlgoritmo", actual.getIdAlgoritmo());
		return "redirect:/home";
	}
	
}
