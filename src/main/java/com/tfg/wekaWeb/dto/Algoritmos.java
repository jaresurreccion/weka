package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Algoritmos")
public class Algoritmos {

	@Id
	Integer idAlgoritmo;
	String nombreAlg;
	String params;
	Date creado;
	Date modificado;
	public Algoritmos(Integer idAlgoritmo, String nombreAlg, String params, Date creado, Date modificado) {
		super();
		this.idAlgoritmo = idAlgoritmo;
		this.nombreAlg = nombreAlg;
		this.params = params;
		this.creado = creado;
		this.modificado = modificado;
	}
	public Integer getIdAlgoritmo() {
		return idAlgoritmo;
	}
	public void setIdAlgoritmo(Integer idAlgoritmo) {
		this.idAlgoritmo = idAlgoritmo;
	}
	public String getNombreAlg() {
		return nombreAlg;
	}
	public void setNombreAlg(String nombreAlg) {
		this.nombreAlg = nombreAlg;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Date getCreado() {
		return creado;
	}
	public void setCreado(Date creado) {
		this.creado = creado;
	}
	public Date getModificado() {
		return modificado;
	}
	public void setModificado(Date modificado) {
		this.modificado = modificado;
	}
	
	
}
