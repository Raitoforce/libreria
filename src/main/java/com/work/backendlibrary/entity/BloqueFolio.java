package com.work.backendlibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="bloque_folio")
public class BloqueFolio implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonView(VentaView.interno.class)
	@EmbeddedId
	private BloqueFolioPK id;

	private int fin;

	private int inicio;

	//bi-directional many-to-one association to Folio
	@ManyToOne
	@JoinColumn(name="folio_idfolios",referencedColumnName="idfolios", insertable=false, updatable=false)
	private Folio folio;

	//bi-directional many-to-one association to Vendedor
	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumn(name="vendedor_clave",referencedColumnName="clave", insertable=false, updatable=false)
	private Vendedor vendedor;

	//bi-directional many-to-one association to Venta
	@JsonIgnore
	@OneToMany(mappedBy="bloqueFolio")
	private List<Venta> ventas;

	public BloqueFolio() {
	}

	public BloqueFolioPK getId() {
		return this.id;
	}

	public void setId(BloqueFolioPK id) {
		this.id = id;
	}

	public int getFin() {
		return this.fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getInicio() {
		return this.inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public Folio getFolio() {
		return this.folio;
	}

	public void setFolio(Folio folio) {
		this.folio = folio;
	}

	@JsonIgnoreProperties({"bloqueFolios"})
	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
}

@Embeddable
class BloqueFolioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@JsonView(VentaView.interno.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Integer id;
	
	@JsonView(VentaView.interno.class)
	@Column(name="vendedor_clave", insertable=false, updatable=false)
	private String vendedorClave;
	@JsonView(VentaView.interno.class)
	@Column(name="folio_idfolios", insertable=false, updatable=false,unique=true)
	private int folioIdfolios;

	public BloqueFolioPK() {
	}
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getVendedorClave() {
		return this.vendedorClave;
	}
	public void setVendedorClave(String vendedorClave) {
		this.vendedorClave = vendedorClave;
	}
	public int getFolioIdfolios() {
		return this.folioIdfolios;
	}
	public void setFolioIdfolios(int folioIdfolios) {
		this.folioIdfolios = folioIdfolios;
	}
	
	
}