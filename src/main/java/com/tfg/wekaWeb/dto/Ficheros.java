package com.tfg.wekaWeb.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Ficheros")
public class Ficheros {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	Integer idFichero;
	
	String contentType;
	String nombreFichero;
	
	@Lob
	byte[] datos;
	
	Integer userId;
	Date creado;
	Date modificado;
	
	public Ficheros() {
		
	}
	public Ficheros( String contentType, String nombreFichero, byte[] datos, Integer userId,
			Date creado, Date modificado) {
		super();
		
		this.contentType = contentType;
		this.nombreFichero = nombreFichero;
		this.userId = userId;
		this.creado = creado;
		this.modificado = modificado;
	}
	public Integer getIdFichero() {
		return idFichero;
	}
	public void setIdFichero(Integer idFichero) {
		this.idFichero = idFichero;
	}
	public String getcontentType() {
		return contentType;
	}
	public void setcontentType(String contentType) {
		this.contentType = contentType;
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
	public byte[] getDatos() {
		return datos;
	}
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
	
	
	
	
	
	
}
