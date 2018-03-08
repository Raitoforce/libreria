package com.work.backendlibrary.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="director")
public class Director {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iddirector")
	private int iddirector;
<<<<<<< HEAD
=======
	
>>>>>>> 383de93f3af5b5c630d6672d343bbfdbeea8c4f6
	@NotNull                //Anotacion para no permitir nulos
	@Basic(optional=false)
	@Column(name="nombre",nullable=false)
	private String nombre;
	@NotNull
	@Basic(optional=false)
	@Column(name="apellidos",nullable=false)
	private String apellidos;
	@Column(name="telefono")
	private String telefono;
	@Column(name="email")
	private String email;
	
	public Director(){
		
	}
	
	public Director(int iddirector, String nombre, String apellidos, String telefono, String email) {
		super();
		this.iddirector = iddirector;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
	}
	
	public int getIddirector() {
		return iddirector;
	}
	public void setIddirector(int iddirector) {
		this.iddirector = iddirector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
