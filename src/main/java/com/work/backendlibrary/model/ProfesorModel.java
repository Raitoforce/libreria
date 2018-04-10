package com.work.backendlibrary.model;


import com.work.backendlibrary.entity.Escuela;

import java.util.List;

public class ProfesorModel {
	private String nombre;
	private String apellidos;
	private String telefono;
	
	public ProfesorModel(){
		
	}
	
	public ProfesorModel(String nombre, String apellidos, String telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
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
}
