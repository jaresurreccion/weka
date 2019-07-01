package com.tfg.wekaWeb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.controller.loginController;
import com.tfg.wekaWeb.dao.UserRepository;
import com.tfg.wekaWeb.dto.User;

@Service
public class UserService {
	   private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	
		@Autowired
		UserRepository userRepository;
		
		
		public Iterable<User> buscarUsuarios(){
			return userRepository.findAll();
		}
		
		public User buscarPorUsuario(String username) throws Exception{
			if(username.isEmpty()) {
				throw new Exception("Usuario invalido");
			}
			return userRepository.findByUsername(username);
			
		}
		
		public Boolean login(String username,String pass) throws Exception{
			User u =  userRepository.findByUsername(username);
			if(u == null) {
				return false;
			}
			
			if(u.getPassword().equals(pass)) {
				return true;
			}
			else {
				return false;
			}
			
		}
}
