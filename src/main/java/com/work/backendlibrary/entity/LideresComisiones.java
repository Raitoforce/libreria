package com.work.backendlibrary.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="lideres_comisiones")
public class LideresComisiones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private LideresComisionesPK id;
	
	private float comision_lider;

	@ManyToOne
	@JoinColumn(name="venta_folio",referencedColumnName="folio", insertable=false, updatable=false)
	@JsonIgnore
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name="profesor",referencedColumnName="idprofesor", insertable=false, updatable=false)
	@JsonIgnoreProperties("escuelas")
	private Profesor lider;
	
	public LideresComisionesPK getId() {
		return id;
	}

	public void setId(LideresComisionesPK id) {
		this.id = id;
	}

	public float getComision_lider() {
		return comision_lider;
	}

	public void setComision_lider(float comision_lider) {
		this.comision_lider = comision_lider;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Profesor getLider() {
		return lider;
	}

	public void setLider(Profesor lider) {
		this.lider = lider;
	}
}