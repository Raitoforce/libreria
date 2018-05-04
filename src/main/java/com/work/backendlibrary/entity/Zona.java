package com.work.backendlibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="zona")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idzona")
	private String idzona;

	@ManyToOne
	@JoinColumn(name="vendedor_clave",referencedColumnName="clave")
	private Vendedor vendedor;

	@JsonIgnoreProperties({"bloqueFolios"})
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Zona() {
	}

	public String getIdzona() {
		return this.idzona;
	}


	public void setIdzona(String idzona) {
		this.idzona = idzona;
	}
}