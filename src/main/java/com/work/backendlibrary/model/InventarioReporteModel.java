package com.work.backendlibrary.model;

import java.util.List;

import com.work.backendlibrary.entity.Stock;

public class InventarioReporteModel {
	String claveProducto;
	String titulo;
	int cantidad;
	List<Stock> stocks;
	
	public String getClaveProducto() {
		return claveProducto;
	}
	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
}
