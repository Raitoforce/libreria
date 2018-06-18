package com.work.backendlibrary.model;

public class PedidosReportModel {
	private int cantidad;
	private String libro_nombre;
	private String libro_grado;
	private float libro_precio;
	private float libro_importe;
	private float precioventa;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getLibro_nombre() {
		return libro_nombre;
	}
	public void setLibro_nombre(String libro_nombre) {
		this.libro_nombre = libro_nombre;
	}
	public String getLibro_grado() {
		return libro_grado;
	}
	public void setLibro_grado(String libro_grado) {
		this.libro_grado = libro_grado;
	}
	public float getLibro_precio() {
		return libro_precio;
	}
	public void setLibro_precio(float libro_precio) {
		this.libro_precio = libro_precio;
	}
	public float getLibro_importe() {
		return libro_importe;
	}
	public void setLibro_importe(float libro_importe) {
		this.libro_importe = libro_importe;
	}
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	
}
