package com.work.backendlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="lideres_comisiones")
public class LideresComisiones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LideresComisionesPK id;
	
	private int comision_lider;

	@ManyToOne
	@JoinColumn(name="venta_folio",referencedColumnName="folio", insertable=false, updatable=false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name="profesor",referencedColumnName="idprofesor", insertable=false, updatable=false)
	private Profesor lider;
	
	public LideresComisionesPK getId() {
		return id;
	}

	public void setId(LideresComisionesPK id) {
		this.id = id;
	}

	public int getComision_lider() {
		return comision_lider;
	}

	public void setComision_lider(int comision_lider) {
		this.comision_lider = comision_lider;
	}

	
}


@Embeddable
class LideresComisionesPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="profesor", insertable=false, updatable=false)
	private Integer profesor;
	
	@Column(name="venta_folio", insertable=false, updatable=false)
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
	
}