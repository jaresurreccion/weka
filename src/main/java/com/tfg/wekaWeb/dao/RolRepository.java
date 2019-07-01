package com.tfg.wekaWeb.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Rol;

@Transactional
public interface RolRepository extends CrudRepository<Rol, Integer> {
	
	
	public Rol findByidUser (Integer idUser);
	
}
