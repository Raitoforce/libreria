package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.sql.Date;

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

import org.springframework.lang.Nullable;

@Entity
@Table(name="Comision")
public class Comision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComision")
	private int idComision;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="monto")
	private String monto;

	@Column(name="tipo")
	private String tipo;


	//bi-directional many-to-one association to Temporada
	@ManyToOne
	@JoinColumn(name="temporada_idTemporada")
	private Temporada temporada;
	
	//bi-directional many-to-one association to Director
		@ManyToOne
		@Nullable
		@JoinColumn(name="director_iddirector")
		private Director director;

	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@Nullable
	@JoinColumn(name="vendedor_clave")
	private Vendedor vendedor;

	public int getIdComision() {
		return idComision;
	}

	public void setIdComision(int idComision) {
		this.idComision = idComision;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}
