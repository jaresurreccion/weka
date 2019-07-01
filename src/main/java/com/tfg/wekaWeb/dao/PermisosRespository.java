package com.tfg.wekaWeb.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Permisos;

@Transactional
public interface PermisosRespository extends CrudRepository<Permisos, Integer> {

	public Permisos findByTipoPermiso (String tipoPermiso);
}
