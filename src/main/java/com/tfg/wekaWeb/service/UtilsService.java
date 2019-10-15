package com.tfg.wekaWeb.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfg.wekaWeb.dao.AlgoritmosRepository;
import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Filtros;
import com.tfg.wekaWeb.dto.SesionTrabajo;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UtilsService;

@Service
public class UtilsService {
	private static final Logger logger = LoggerFactory.getLogger(UtilsService.class);

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
	public void printAtributesSession(HttpSession session) {
		 Enumeration e = (Enumeration) (session.getAttributeNames());
	        while ( e.hasMoreElements())
	        {
	            Object tring;
	            if((tring = e.nextElement())!=null)
	            {
	               System.out.println(tring.toString() +":"+session.getValue((String) tring));
	            }

	        }
	
	}

}
