package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ficheros")
public class Ficheros {

	@Id
	Integer idFichero;
	String ruta;
	String nombreFichero;
	Integer userId;
	Date adjuntado;
	Integer IdAlgoritmo;
	Date creado;
	Date modificado;
	
	public Ficheros(Integer idFichero, String ruta, String nombreFichero, Integer userId, Date adjuntado,
			Integer idAlgoritmo, Date creado, Date modificado) {
		super();
		this.idFichero = idFichero;
		this.ruta = ruta;
		this.nombreFichero = nombreFichero;
		this.userId = userId;
		this.adjuntado = adjuntado;
		IdAlgoritmo = idAlgoritmo;
		this.creado = creado;
		this.modificado = modificado;
	}
	public Integer getIdFichero() {
		return idFichero;
	}
	public void setIdFichero(Integer idFichero) {
		this.idFichero = idFichero;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getNombreFichero() {
		return nombreFichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getAdjuntado() {
		return adjuntado;
	}
	public void setAdjuntado(Date adjuntado) {
		this.adjuntado = adjuntado;
	}
	public Integer getIdAlgoritmo() {
		return IdAlgoritmo;
	}
	public void setIdAlgoritmo(Integer idAlgoritmo) {
		IdAlgoritmo = idAlgoritmo;
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
