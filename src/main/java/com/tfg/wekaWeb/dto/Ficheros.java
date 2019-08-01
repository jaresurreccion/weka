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
	
	String nAtributos;
	String clase;
	Integer idUsuario;
	Date creado;
	Date modificado;
	String comentario;
	String numInstancias;
	String ruta;
	
	public Ficheros() {
		
	}
	public Ficheros( String contentType, String nombreFichero, String ruta, Integer idUsuario,
			Date creado, Date modificado,String comentario,String clase,String nAtributos,String numInstancias) {
		super();
		
		this.contentType = contentType;
		this.nombreFichero = nombreFichero;
		this.idUsuario = idUsuario;
		this.creado = creado;
		this.modificado = modificado;
		this.comentario = comentario;
		this.ruta = ruta;
		this.clase= clase;
		this.nAtributos = nAtributos;
		this.numInstancias = numInstancias;
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
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getnAtributos() {
		return nAtributos;
	}

	public void setnAtributos(String nAtributos) {
		this.nAtributos = nAtributos;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getNumInstancias() {
		return numInstancias;
	}
	public void setNumInstancias(String numInstancias) {
		this.numInstancias = numInstancias;
	}
	

	
	
	
	
	
	
}
