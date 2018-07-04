package com.work.backendlibrary.model;

import java.sql.Date;

public class ComisionModel {
	private int idComision;
	private Date fecha;
	private float monto;
	private String tipo;
	private int temporada;
	private int director;
	private String vendedor;
	
	public int getIdComision() {
		return idComision;
	}
	public void setIdComision(int idComision) {
		this.idComision = idComision;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public int getDirector() {
		return director;
	}
	public void setDirector(int director) {
		this.director = director;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
}
