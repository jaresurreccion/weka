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
	@GeneratedValue
	Integer idFichero;
	
	String contentType;
	String nombreFichero;
	
	
	
	Integer idUsuario;
	Date creado;
	Date modificado;
	String comentario;
	
	@Lob
	byte[] datos;
	
	public Ficheros() {
		
	}
	public Ficheros( String contentType, String nombreFichero, byte[] datos, Integer idUsuario,
			Date creado, Date modificado,String comentario) {
		super();
		
		this.contentType = contentType;
		this.nombreFichero = nombreFichero;
		this.idUsuario = idUsuario;
		this.creado = creado;
		this.modificado = modificado;
		this.comentario = comentario;
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
	public Integer getidUsuario() {
		return idUsuario;
	}
	public void setidUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	
	
	
}
