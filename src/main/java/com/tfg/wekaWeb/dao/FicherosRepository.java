package com.tfg.wekaWeb.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Ficheros;

@Transactional
public interface FicherosRepository extends CrudRepository<Ficheros, Integer> {

	@Query(value="SELECT * from Ficheros where IdAlgoritmo = :IdAlgoritmo",nativeQuery=true)
	public Iterable<Ficheros> ficherosByAlgoritmo(Integer IdAlgoritmo);
	
}
