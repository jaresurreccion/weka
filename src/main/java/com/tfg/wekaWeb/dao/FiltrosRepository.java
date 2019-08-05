package com.tfg.wekaWeb.dao;

import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Filtros;


public interface FiltrosRepository  extends CrudRepository<Filtros, Integer>{
	
	public Filtros findByidSession(Integer idSession);


}
