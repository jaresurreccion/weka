package com.tfg.wekaWeb.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.User;

@Transactional
public interface FicherosRepository extends CrudRepository<Ficheros, Integer> {

	public Ficheros findByidUsuario (int idUsuario);

}
