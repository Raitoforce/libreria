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
@Table(name="cuentas_por_cobrar")
public class CuentasPorCobrar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movimiento")
	private int idMovimiento;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float pago;
	
	@ManyToOne
	@JoinColumn(name="venta_folio",referencedColumnName="folio")
	private Venta venta;
	
	public CuentasPorCobrar() {
	
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPago() {
		return pago;
	}

	public void setPago(float pago) {
		this.pago = pago;
	}

}
