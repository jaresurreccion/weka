package com.tfg.wekaWeb.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.User;
import com.tfg.wekaWeb.dto.SesionTrabajo;
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UserService;
import com.tfg.wekaWeb.service.wekaService;

//@RestController
@Controller
public class loginController implements ErrorController{
	   private static final Logger logger = LoggerFactory.getLogger(loginController.class);

	   HttpSession session;

		@Autowired
		private UserService userService;
		
		@Autowired
		private FicherosService ficherosService;
		
		@Autowired
		private SesionTrabajoService trabajosService;
		
		@Autowired
		private wekaService wekaService;

		
		@RequestMapping(value="/",method = RequestMethod.GET)
		public String login(Model model,HttpServletRequest request) {
			session = request.getSession(false);
			if(session != null){
				request.getSession().invalidate();
			}
			
			model.addAttribute("index",true);
			return "index";
		}
		
		@RequestMapping(value="/nuevoUsuario",method = RequestMethod.GET)
		public String nuevoUsuario(Model model) {		
			model.addAttribute("index",true);
			return "nuevoUsuario";
		}
		
		@RequestMapping(value="/home", method = RequestMethod.POST)
		public String loginOK(String user,String pass,ModelMap model,HttpServletResponse response,HttpServletRequest request,RedirectAttributes modelflash) throws Exception  {
			Boolean login = userService.login(user, pass);
			if(login) {
				User e = userService.buscarPorUsuario(user);
				Cookie cookie = new Cookie("username",e.getUsername());
				response.addCookie(cookie);
				Cookie cookieid= new Cookie("userId",e.getId().toString());
				response.addCookie(cookieid);
				session = request.getSession(true);
				session.setAttribute("username", e.getUsername());
				session.setAttribute("idUser", e.getId());
				session.setAttribute("trabajo", (List<SesionTrabajo>) trabajosService.buscarSesiones(e.getId()));
				return "home";
			}
			else {		
				modelflash.addFlashAttribute("error",true);
				return "redirect:/";
			}
		}
		
		@RequestMapping(value="/createUsuario", method = RequestMethod.POST)
		public String createUsuario(String nombre,String apellido1,String apellido2,
			String username,String pass,ModelMap model,HttpServletResponse response,HttpServletRequest request) throws Exception  {
				User e = new User(apellido1, apellido2, nombre, username, pass, 1, new Date(), new Date(), new Date());
				e = userService.saveUser(e);
				if(e!= null) {
					Cookie cookie = new Cookie("username",e.getUsername());
					response.addCookie(cookie);
					Cookie cookieid= new Cookie("userId",e.getId().toString());
					response.addCookie(cookieid);
					session = request.getSession(true);
					session.setAttribute("username", e.getUsername());
					session.setAttribute("idUser", e.getId());
					session.setAttribute("trabajo", (List<SesionTrabajo>) trabajosService.buscarSesiones(e.getId()));
					return "home";
				}else {
					return "index";
				}

		}
		
		@RequestMapping(value="/algoritmos",method = RequestMethod.GET)
		public String Algoritmos(ModelMap model, @CookieValue(value = "userId", defaultValue = "Attat") String userId) {
			List<Ficheros> ficherosXusuario = ficherosService.getFicherosByIdSession(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
			model.addAttribute("ListaFicheros",ficherosXusuario);
			return "algoritmos";
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
		public String miHome(ModelMap model,HttpServletRequest request) {
			session = request.getSession();
			if(session==null) {
				return "redirect:/error";
			}
			Enumeration<String> e = session.getAttributeNames();
			session.setAttribute("trabajo", (List<SesionTrabajo>) trabajosService.buscarSesiones(Integer.parseInt(session.getAttribute("idUser").toString())));
			while(e.hasMoreElements()){
				System.out.println(e.nextElement());
			}
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
		
		@RequestMapping(value="/ficheros",method = RequestMethod.GET)
		public String Ficheros(ModelMap model, @CookieValue(value = "userId", defaultValue = "Attat") String userId) {
			List<Ficheros> ficherosXusuario = ficherosService.getFicherosByIdSession(Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString()));
			if(ficherosXusuario.size() <= 0 || ficherosXusuario == null) {
				return "ficheros";
			}
			else {
				model.addAttribute("ListaFicheros",ficherosXusuario);	
			}
			
			
			return "ficheros";
		}

		@RequestMapping(value="/filtro",method = RequestMethod.GET)
		public String Filtro(ModelMap model,HttpServletRequest request) throws NumberFormatException, IOException {
			session = request.getSession();
			session.setAttribute("atributos", (List<String []>) wekaService.getAtributos(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString())));
			return "filtros";
		}
		
		@RequestMapping(value = "/error")
	    public String error() {
	        return "redirect:/";
	    }

	    @Override
	    public String getErrorPath() {
	        return "/error";
	    }
	    
	    @RequestMapping(value="/resultados",method = RequestMethod.GET)
		public String Resultados(ModelMap model,HttpServletRequest request) throws NumberFormatException, IOException {
			session = request.getSession(false);
			return "resultados";
		}
		
		
		
		
}
