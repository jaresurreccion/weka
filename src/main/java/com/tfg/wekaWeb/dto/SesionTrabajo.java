package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SesionTrabajo")
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
	Integer idFiltros;
	String nombreFiltros;
	String nombreFile;
	String nombreAlg;

	public SesionTrabajo(Integer idUsuario, String nombre, Integer idFile, Integer idAlgoritmo, Integer idFiltros,Date fechaCreacion,
			Date fechaActualizacion,String nombreFile, String nombreAlg,String nombreFiltros) {
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.idFile = idFile;
		this.idAlgoritmo = idAlgoritmo;
		this.fechaCreacion = fechaCreacion;
		this.idFiltros = idFiltros;
		this.nombreFile= nombreFile;
		this.nombreAlg = nombreAlg;
		this.nombreFiltros = nombreFiltros;
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

	public Integer getIdFiltros() {
		return idFiltros;
	}

	public void setIdFiltros(Integer idFiltros) {
		this.idFiltros = idFiltros;
	}
	
	

	public String getNombreFiltros() {
		return nombreFiltros;
	}

	public void setNombreFiltros(String nombreFiltros) {
		this.nombreFiltros = nombreFiltros;
	}

	public String getNombreFile() {
		return nombreFile;
	}

	public void setNombreFile(String nombreFile) {
		this.nombreFile = nombreFile;
	}

	public String getNombreAlg() {
		return nombreAlg;
	}

	public void setNombreAlg(String nombreAlg) {
		this.nombreAlg = nombreAlg;
	}

	@Override
	public String toString() {
		return "SesionTrabajo [idSesion=" + idSesion + ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", idFile="
				+ idFile + ", idAlgoritmo=" + idAlgoritmo + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}

}