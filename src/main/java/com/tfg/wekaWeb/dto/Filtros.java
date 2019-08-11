package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Filtros")
public class Filtros {

	@Id
	@GeneratedValue
	Integer idFiltros;
	String tipo;
	String atributosRemove;
	Integer idSession;
	Integer idFichero;
	Date fechaCreacion;
	
	
	
	public Filtros() {
		super();
	}
	
	public Filtros(String tipo, String atributosRemove, Integer idSession, Integer idFichero,Date fechaCreacion) {
		this.tipo = tipo;
		this.atributosRemove = atributosRemove;
		this.idSession = idSession;
		this.idFichero = idFichero;
		this.fechaCreacion=fechaCreacion;
	}
	public Integer getIdFiltros() {
		return idFiltros;
	}
	public void setIdFiltros(Integer idFiltros) {
		this.idFiltros = idFiltros;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAtributosRemove() {
		return atributosRemove;
	}
	public void setAtributosRemove(String atributosRemove) {
		this.atributosRemove = atributosRemove;
	}
	public Integer getIdSession() {
		return idSession;
	}
	public void setIdSession(Integer idSession) {
		this.idSession = idSession;
	}
	public Integer getIdFichero() {
		return idFichero;
	}
	public void setIdFichero(Integer idFichero) {
		this.idFichero = idFichero;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
}
