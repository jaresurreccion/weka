package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="SesionTrabajo")
public class SesionTrabajo {
	
	@Id
	@GeneratedValue
	Integer idSesion;
	
	Integer idUsuario;
	String nombre;
	Integer idFile;
	Integer idAlgoritmo;
	Date fechaCreacion;
	Date fechaActualizacion;
	
	public SesionTrabajo(Integer idUsuario, String nombre,Integer idFile, Integer idAlgoritmo, Date fechaCreacion,
			Date fechaActualizacion) {
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.idFile = idFile;
		this.idAlgoritmo = idAlgoritmo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}
	
	
	
	
	public SesionTrabajo() {
		super();
	}




	public Integer getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdFile() {
		return idFile;
	}
	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}
	public Integer getIdAlgoritmo() {
		return idAlgoritmo;
	}
	public void setIdAlgoritmo(Integer idAlgoritmo) {
		this.idAlgoritmo = idAlgoritmo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}




	@Override
	public String toString() {
		return "SesionTrabajo [idSesion=" + idSesion + ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", idFile="
				+ idFile + ", idAlgoritmo=" + idAlgoritmo + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}
	
	
	

	
}