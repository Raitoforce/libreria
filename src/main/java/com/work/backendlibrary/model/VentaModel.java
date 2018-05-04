package com.work.backendlibrary.model;

import java.sql.Date;

public class VentaModel {
	private String folio;
	private Date fecha;
	private float comision_vendedor;
	private float comision_profesor;
	private float comision_director;
	private String vendedor_clave;
	private int idfolios;
	private String escuela_clave;
	private int idprofesor;
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getComision_vendedor() {
		return comision_vendedor;
	}
	public void setComision_vendedor(float comision_vendedor) {
		this.comision_vendedor = comision_vendedor;
	}
	public float getComision_profesor() {
		return comision_profesor;
	}
	public void setComision_profesor(float comision_profesor) {
		this.comision_profesor = comision_profesor;
	}
	public float getComision_director() {
		return comision_director;
	}
	public void setComision_director(float comision_director) {
		this.comision_director = comision_director;
	}
	public String getVendedor_clave() {
		return vendedor_clave;
	}
	public void setVendedor_clave(String vendedor_clave) {
		this.vendedor_clave = vendedor_clave;
	}
	public int getIdfolios() {
		return idfolios;
	}
	public void setIdfolios(int idfolios) {
		this.idfolios = idfolios;
	}
	public String getEscuela_clave() {
		return escuela_clave;
	}
	public void setEscuela_clave(String escuela_clave) {
		this.escuela_clave = escuela_clave;
	}
	public int getIdprofesor() {
		return idprofesor;
	}
	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}
}
