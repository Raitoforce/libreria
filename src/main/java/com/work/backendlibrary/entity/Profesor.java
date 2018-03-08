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
@Table(name = "profesor")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprofesor")
	private int idprofesor;
	@NotNull
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	@NotNull
	@Basic(optional = false)
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "telefono")
	private String telefono;

	// private String email;
	public int getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
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

	public Profesor() {
	}

	public Profesor(int idprofesor, @NotNull String nombre, @NotNull String apellidos, String telefono) {
		super();
		this.idprofesor = idprofesor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}

}
