package com.tfg.wekaWeb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.SesionTrabajo;

public interface SesionTrabajoRepository extends CrudRepository<SesionTrabajo, Integer>{

	public List<SesionTrabajo> findByidUsuario (Integer idUsuario);
	
	
}
