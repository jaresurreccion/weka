package com.tfg.wekaWeb.controller;

import java.io.IOException;
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

import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.Filtros;
import com.tfg.wekaWeb.dto.SesionTrabajo;
import com.tfg.wekaWeb.dto.User;
import com.tfg.wekaWeb.service.AlgoritmosService;
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.FiltrosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UserService;
import com.tfg.wekaWeb.service.UtilsService;
import com.tfg.wekaWeb.service.wekaService;

@Controller
public class sessionController {
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);

	HttpSession session;
	private UtilsService utils = new UtilsService();

	@Autowired
	private SesionTrabajoService trabajosService;

	@Autowired
	private wekaService wekaService;

	@Autowired
	private AlgoritmosService algoritmoService;

	@Autowired
	private FiltrosService filtrosService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FicherosService ficherosService;

	@RequestMapping(value = "/crearSesion", method = RequestMethod.POST)
	public String createUsuario(String nombre, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		session = utils.isValidSession(request);
		Integer idUser = Integer.parseInt(session.getAttribute("idUser").toString());
		trabajosService.nuevaSesionTrabajo(idUser, nombre);
		session.setAttribute("trabajo", trabajosService.buscarSesiones(idUser));

		return "home";
	}

	@RequestMapping(value = "/activateSession/{idSession}", method = RequestMethod.GET)
	public String activarSesion(ModelMap model, @CookieValue(value = "userId", defaultValue = "Attat") String userId,
			HttpServletRequest request, @PathVariable String idSession) throws NumberFormatException, IOException {
		session = utils.isValidSession(request);

		SesionTrabajo actual = trabajosService.buscarXid(Integer.parseInt(idSession));
		if (actual != null) {
			session.setAttribute("sesionActiva", true);
			session.setAttribute("sesionActivaNombre", actual.getNombre());
			session.setAttribute("sesionActivaIdSesion", actual.getIdSesion());
			session.setAttribute("sesionActivaIdFile", actual.getIdFile());
			session.setAttribute("sesionActivaIdAlgoritmo", actual.getIdAlgoritmo());
			session.setAttribute("sesionActivaIdFiltros", actual.getIdFiltros());
		}
		if (actual.getIdFile() != null) {
			session.setAttribute("atributos", (List<String[]>) wekaService.getAtributos(actual.getIdFile()));
			session.setAttribute("filtroActivo", true);
		}

		if (actual.getIdFiltros() != null) {
			Filtros filtro = filtrosService.findByIdFiltros(actual.getIdFiltros());
			if (filtro != null) {
				session.setAttribute("filtroActivoRemove", filtro.getAtributosRemove());
				session.setAttribute("filtroActivoRemoveName", filtro.getAtributosRemoveName());
				session.setAttribute("filtroActivoTipo", Integer.parseInt(filtro.getTipo()) == 1 ? "Supervisado" : "No supervisado");
				session.setAttribute("algoritmoActivo", true);
			}
		}

		if (actual.getIdAlgoritmo() != null) {
			Algoritmos alg = algoritmoService.findById(actual.getIdAlgoritmo());
			if (alg != null) {		
				session.setAttribute("algoritmoActivoId", alg.getIdAlgoritmo());
				session.setAttribute("algoritmoActivoNombre", alg.getNombreAlg());
			}
		}

		utils.printAtributesSession(session);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/deleteSession/{idSession}", method = RequestMethod.GET)
	public String deleteSession(ModelMap model, @CookieValue(value = "userId", defaultValue = "Attat") String userId,
			HttpServletRequest request, @PathVariable String idSession) {
		session = utils.isValidSession(request);
		trabajosService.deleteSession(Integer.parseInt(idSession));
		session.removeAttribute("sesionActiva");
		session.removeAttribute("sesionActivaNombre");
		session.removeAttribute("sesionActivaIdSesion");
		session.removeAttribute("sesionActivaIdFile");
		session.removeAttribute("sesionActivaIdAlgoritmo");
		session.removeAttribute("sesionActivaIdFiltros");
		session.removeAttribute("atributos");
		session.removeAttribute("filtroActivoRemove");
		session.removeAttribute("filtroActivoRemoveName");
		session.removeAttribute("filtroActivoTipo");
		session.removeAttribute("algoritmoActivo");
		session.removeAttribute("algoritmoActivoId");
		session.removeAttribute("algoritmoActivoNombre");
		return "redirect:/home";
	}

	@RequestMapping(value = "/configuracion", method = RequestMethod.GET)
	public String configuracion(HttpServletRequest request) {
		session = utils.isValidSession(request);
		session.setAttribute("listaUsuarios", userService.buscarUsuarios());
		session.setAttribute("listaFicheros", ficherosService.getFiles());
		return "configuracion";
	}
	
	@RequestMapping(value = "/delete/{idUser}", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request,@PathVariable String idUser) {
		session = utils.isValidSession(request);
		User e = userService.findById(Integer.parseInt(idUser));
		userService.deleteUser(e);
		session.setAttribute("listaUsuarios", userService.buscarUsuarios());
		session.setAttribute("listaFicheros", ficherosService.getFiles());
		return "redirect:/configuracion";
	}
	
}
