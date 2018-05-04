package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="historial_venta")
public class HistorialVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHistorial;

	private Integer entregados;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_confirmacion")
	private Date fechaConfirmacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_solicitud")
	private Date fechaSolicitud;

	@Column(name="motivo")
	private String motivo;

	@Column(name="pedidos")
	private int pedidos;

	@Column(name="tipo_movimiento")
	private String tipoMovimiento;

	@ManyToOne
	@JoinColumn(name="venta_folio",referencedColumnName="folio")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name="libro_clave_producto",referencedColumnName="clave_producto")
	private Libro libro;
	
	public HistorialVenta() {
	
	}

	public int getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Integer getEntregados() {
		return entregados;
	}

	public void setEntregados(Integer entregados) {
		this.entregados = entregados;
	}

	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getPedidos() {
		return pedidos;
	}

	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}