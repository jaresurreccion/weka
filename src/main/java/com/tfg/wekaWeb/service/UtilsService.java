package com.tfg.wekaWeb.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilsService {
	   private static final Logger logger = LoggerFactory.getLogger(UtilsService.class);

	   
	   
	   public UtilsService() {}

	public HttpSession createSession(HttpServletRequest request) {
		return request.getSession(true);
	   }
	   
	   
	public HttpSession isValidSession(HttpServletRequest request) {
		   HttpSession validSession = request.getSession(false);
		 if(validSession != null) logger.info(request.getContextPath() + "Sesion:" + validSession.toString());
		   return validSession;
	   }
	   
	   public void deleteSession(HttpServletRequest request) {
		   if (request.getSession(false) != null) {
			   request.getSession().invalidate();  
		   }
	   }
	   
	
}
