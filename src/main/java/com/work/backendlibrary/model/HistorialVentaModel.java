package com.work.backendlibrary.model;

import java.sql.Date;
import java.sql.Timestamp;

public class HistorialVentaModel {
	private Integer idHistorial;
	private int pedidos;
	private int entregados;
	private String venta_folio;
	private String libro_clave;
	private Date fecha_solicitud;
	private Timestamp fecha_confirmacion;
	private String tipo_movimiento;
	private String motivo;
	
	public int getIdHistorial() {
		return idHistorial;
	}
	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}
	public int getPedidos() {
		return pedidos;
	}
	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}
	public int getEntregados() {
		return entregados;
	}
	public void setEntregados(int entregados) {
		this.entregados = entregados;
	}
	public String getVenta_folio() {
		return venta_folio;
	}
	public void setVenta_folio(String venta_folio) {
		this.venta_folio = venta_folio;
	}
	public String getLibro_clave() {
		return libro_clave;
	}
	public void setLibro_clave(String libro_clave) {
		this.libro_clave = libro_clave;
	}
	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	public Timestamp getFecha_confirmacion() {
		return fecha_confirmacion;
	}
	public void setFecha_confirmacion(Timestamp fecha_confirmacion) {
		this.fecha_confirmacion = fecha_confirmacion;
	}
	public String getTipo_movimiento() {
		return tipo_movimiento;
	}
	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
