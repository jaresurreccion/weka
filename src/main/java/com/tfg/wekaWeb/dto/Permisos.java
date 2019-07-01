package com.tfg.wekaWeb.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Permisos")
public class Permisos {

	@Id
	Integer idPermiso;
	String tipoPermiso;
	public Permisos(Integer idPermiso, String tipoPermiso) {
		super();
		this.idPermiso = idPermiso;
		this.tipoPermiso = tipoPermiso;
	}
	
	public Integer getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}
	public String getTipoPermiso() {
		return tipoPermiso;
	}
	public void setTipoPermiso(String tipoPermiso) {
		this.tipoPermiso = tipoPermiso;
	}
	
	
}
