package com.work.backendlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String fecha;

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

}
