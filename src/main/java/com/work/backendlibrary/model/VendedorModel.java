package com.work.backendlibrary.model;


public class VendedorModel {
    private String clave;

    private String nombre;

    private String apellidos;

    private String telefono;
    
    public VendedorModel(){
    	
    }
    
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

	public VendedorModel(String clave, String nombre, String apellidos, String telefono) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}
    
    

}
