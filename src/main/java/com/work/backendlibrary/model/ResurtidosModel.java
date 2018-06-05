package com.work.backendlibrary.model;

import java.sql.Date;
import java.util.List;

public class ResurtidosModel {
	private String folio;
	private List<Integer> numresurtidos;
	private List<Date> fechas;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public List<Integer> getNumresurtidos() {
		return numresurtidos;
	}
	public void setNumresurtidos(List<Integer> numresurtidos) {
		this.numresurtidos = numresurtidos;
	}
	public List<Date> getFechas() {
		return fechas;
	}
	public void setFechas(List<Date> fechas) {
		this.fechas = fechas;
	}
	
	
}
