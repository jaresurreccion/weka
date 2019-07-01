package com.tfg.wekaWeb.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rol")

public class Rol {

	@Id
	Integer idRol;
	String tipoRol;
	Integer idPermiso;
	Integer idUser;
	
	
	
	public Rol(Integer idRol, String tipoRol, Integer idPermiso, Integer idUser) {
		super();
		this.idRol = idRol;
		this.tipoRol = tipoRol;
		this.idPermiso = idPermiso;
		this.idUser = idUser;
	}
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getTipoRol() {
		return tipoRol;
	}
	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}
	public Integer getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	
	
}
