package com.work.backendlibrary.entity;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="director")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Director {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iddirector")
	private int iddirector;

	
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
	
	@OneToMany(mappedBy="director",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	List<Escuela> escuelas;
	
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
	
	@PreRemove
	public void Nullable(){
		for (Iterator<Escuela> iterator = escuelas.iterator(); iterator.hasNext();) {
			Escuela escuela = (Escuela) iterator.next();
			escuela.setDirector(null);
		}
	}
	
}
