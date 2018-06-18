package com.work.backendlibrary.model;


public class EscuelaModel {
	private String clave;
	private String nombre;
	private String codigoPostal;
	private String direccion;
	private String colonia;
	private String email;
	private String estado;
	private String municipio;
	private String telefono;
	private String turno;
	private DirectorModel director;
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public DirectorModel getDirector() {
		return director;
	}
	public void setDirector(DirectorModel director) {
		this.director = director;
	}
	
	public EscuelaModel(String clave, String nombre, String codigoPostal, String direccion, String colonia,
			String email, String estado, String municipio, String telefono, String turno, DirectorModel director) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.colonia = colonia;
		this.email = email;
		this.estado = estado;
		this.municipio = municipio;
		this.telefono = telefono;
		this.turno = turno;
		this.director = director;
	}
	
	public EscuelaModel(){
		
	}
}
