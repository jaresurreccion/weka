package com.tfg.wekaWeb.dto;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User  {

	@Id
	Integer id;
	String primerApellido;
	String segundoApellido;
	String nombre;
	String username;
	String password;
	Integer flag;
	Date creado;
	Date modificado;
	Date last_access;
	
	
	
	public User() {
		
	}
	public User(Integer id, String primerApellido, String segundoApellido, String nombre, String username, String password,
			Integer flag, Date creado, Date modificado, Date last_access) {
		super();
		this.id = id;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.flag = flag;
		this.creado = creado;
		this.modificado = modificado;
		this.last_access = last_access;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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
	public Date getLast_access() {
		return last_access;
	}
	public void setLast_access(Date last_access) {
		this.last_access = last_access;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
