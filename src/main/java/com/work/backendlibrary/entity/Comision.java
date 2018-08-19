package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name="comision")
public class Comision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComision")
	private int idComision;
	
	@Column(name="fecha")
	//@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="monto")
	private float monto;

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
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="lideres_profesor", referencedColumnName="profesor"),
		@JoinColumn(name="lideres_venta", referencedColumnName="venta_folio")
		})
	private LideresComisiones lider;

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

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
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

	public LideresComisiones getLider() {
		return lider;
	}

	public void setLider(LideresComisiones lider) {
		this.lider = lider;
	}
}
