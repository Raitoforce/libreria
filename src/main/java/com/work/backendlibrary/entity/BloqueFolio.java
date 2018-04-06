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
@Table(name="bloque_folio")
public class BloqueFolio implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private BloqueFolioPK id;

	private int fin;

	private int inicio;

	//bi-directional many-to-one association to Folio
	@ManyToOne
	@JoinColumn(name="folio_idfolios",referencedColumnName="idfolios", insertable=false, updatable=false)
	private Folio folio;

	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name="vendedor_clave",referencedColumnName="clave", insertable=false, updatable=false)
	private Vendedor vendedor;

	/*//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="bloqueFolio")
	private List<Venta> ventas;*/

	public BloqueFolio() {
	}

	/*public BloqueFolioPK getId() {
		return this.id;
	}

	public void setId(BloqueFolioPK id) {
		this.id = id;
	}*/

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

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	/*public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setBloqueFolio(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setBloqueFolio(null);

		return venta;
	}*/

}

@Embeddable
class BloqueFolioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="vendedor_clave", insertable=false, updatable=false)
	private String vendedorClave;

	@Column(name="folio_idfolios", insertable=false, updatable=false)
	private int folioIdfolios;

	public BloqueFolioPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BloqueFolioPK)) {
			return false;
		}
		BloqueFolioPK castOther = (BloqueFolioPK)other;
		return 
			this.vendedorClave.equals(castOther.vendedorClave)
			&& (this.folioIdfolios == castOther.folioIdfolios);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vendedorClave.hashCode();
		hash = hash * prime + this.folioIdfolios;
		
		return hash;
	}
}