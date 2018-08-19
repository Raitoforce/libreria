package com.work.backendlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LideresComisionesPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="profesor", insertable=true, updatable=true)
	private Integer profesor;
	
	@Column(name="venta_folio", insertable=true, updatable=true)
	private String venta;

	public int getProfesor() {
		return profesor;
	}

	public void setProfesor(int profesor) {
		this.profesor = profesor;
	}

	public String getVenta() {
		return venta;
	}

	public void setVenta(String venta) {
		this.venta = venta;
	}

	public LideresComisionesPK(Integer profesor, String venta) {
		super();
		this.profesor = profesor;
		this.venta = venta;
	}

	public LideresComisionesPK() {
		
	}
	
}