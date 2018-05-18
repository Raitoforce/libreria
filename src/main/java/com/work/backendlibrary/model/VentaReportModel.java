package com.work.backendlibrary.model;

import java.sql.Date;
import java.util.List;

import com.work.backendlibrary.converter.Numero_a_Letra;

public class VentaReportModel{
	
	Numero_a_Letra numLetra=new Numero_a_Letra();
	
	private String escuela_nombre;
	private String profesor_nombre;
	private String escuela_domicilio;
	private String municipio_escuela;
	private String profesor_telefono;
	private Date fecha;
	private String folio;
	private float subtotal;
	private float descuento;
	private float cantidadTotal;
	private String cantidad;
	private List<PedidosReportModel> pedidos;
	
	public String getEscuela_nombre() {
		return escuela_nombre;
	}

	public void setEscuela_nombre(String escuela_nombre) {
		this.escuela_nombre = escuela_nombre;
	}

	public String getProfesor_nombre() {
		return profesor_nombre;
	}

	public void setProfesor_nombre(String profesor_nombre) {
		this.profesor_nombre = profesor_nombre;
	}

	public String getEscuela_domicilio() {
		return escuela_domicilio;
	}

	public void setEscuela_domicilio(String escuela_domicilio) {
		this.escuela_domicilio = escuela_domicilio;
	}



	public String getMunicipio_escuela() {
		return municipio_escuela;
	}



	public void setMunicipio_escuela(String municipio_escuela) {
		this.municipio_escuela = municipio_escuela;
	}



	public String getProfesor_telefono() {
		return profesor_telefono;
	}



	public void setProfesor_telefono(String profesor_telefono) {
		this.profesor_telefono = profesor_telefono;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(float cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public List<PedidosReportModel> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidosReportModel> pedidos) {
		this.pedidos = pedidos;
	}

	public void Calcular(float comision){
		float subtotalC=0;
		float descuentoC=0;
		for (PedidosReportModel pedido: pedidos) {
			subtotalC+=pedido.getLibro_precio()*pedido.getCantidad();
			descuentoC+=pedido.getCantidad()*comision;
		}
		setSubtotal(subtotalC);
		setDescuento(descuentoC);
		setCantidadTotal(subtotalC-descuentoC);
		setCantidad(numLetra.Convertir(String.valueOf(this.getCantidadTotal()),true));
	}
}
