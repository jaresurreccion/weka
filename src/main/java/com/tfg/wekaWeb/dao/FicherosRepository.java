package com.tfg.wekaWeb.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.wekaWeb.dto.Ficheros;

@Transactional
public interface FicherosRepository extends CrudRepository<Ficheros, Integer> {

	public Ficheros findByidSession (int idSession);
	
	@Query("SELECT p FROM Ficheros p WHERE p.idSession = :idSession")
	public List<Ficheros> findAllByIdSession(@Param("idSession") int idSession);

}
