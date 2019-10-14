package com.tfg.wekaWeb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tfg.wekaWeb.dao.AlgoritmosRepository;
import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Filtros;
import com.tfg.wekaWeb.dto.SesionTrabajo;

@Controller
public class UtilsService {
	private static final Logger logger = LoggerFactory.getLogger(UtilsService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private FicherosService ficherosService;
	
	@Autowired
	private FiltrosService filtrosService;
	
	@Autowired
	private AlgoritmosRepository algoritmoRepository;

	@Autowired
	private SesionTrabajoService trabajosService;

	@Autowired
	private wekaService wekaService;

	public UtilsService() {
	}

	public HttpSession createSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	public HttpSession isValidSession(HttpServletRequest request) {
		HttpSession validSession = request.getSession(false);
		if (validSession != null)
			logger.info(request.getContextPath() + "Sesion:" + validSession.toString());
		return validSession;
	}

	public void deleteSession(HttpServletRequest request) {
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
	}

	public HttpSession fillSessionAtributes(HttpSession session, String IdSession)
			throws NumberFormatException, IOException {
		SesionTrabajo actual = trabajosService.buscarXid(Integer.parseInt(IdSession));
		if (actual != null) {
			session.setAttribute("sesionActiva", true);
			session.setAttribute("sesionActivaNombre", actual.getNombre());
			session.setAttribute("sesionActivaIdSesion", actual.getIdSesion());
			session.setAttribute("sesionActivaIdFile", actual.getIdFile());
			session.setAttribute("sesionActivaIdAlgoritmo", actual.getIdAlgoritmo());
		}
		if (actual.getIdFile() != null) {
			session.setAttribute("atributos", (List<String[]>) wekaService.getAtributos(actual.getIdFile()));
		}
		
		Filtros filtro = filtrosService.findByIdSession(Integer.parseInt(IdSession));
		if (filtro != null) {
			session.setAttribute("filtroActivo", true);
	        session.setAttribute("filtroActivoRemove", filtro.getAtributosRemove());
	        session.setAttribute("filtroActivoTipo", filtro.getTipo());
		}
        

		Optional<Algoritmos> alg = algoritmoRepository.findById(actual.getIdAlgoritmo());
		if (alg != null) {
			session.setAttribute("algoritmoActivo", true);
			session.setAttribute("algoritmoActivoId", alg.get().getIdAlgoritmo());
			session.setAttribute("algoritmoActivoNombre", alg.get().getNombreAlg());
		}
		return session;
	}

}
