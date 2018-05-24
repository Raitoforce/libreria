package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

@Entity
@Table(name="venta")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonView(VentaView.Todo.class)
	@Id
	@Column(name="folio")
	private String folio;
	
	@JsonView(VentaView.interno.class)
	@Column(name="comision_director")
	private float comisionDirector;

	@JsonView(VentaView.interno.class)
	@Column(name="comision_profesor")
	private float comisionProfesor;

	@JsonView(VentaView.interno.class)
	@Column(name="comision_vendedor")
	private float comisionVendedor;

	@JsonView(VentaView.interno.class)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to BloqueFolio
	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id", referencedColumnName="id"),
		@JoinColumn(name="idfolios", referencedColumnName="folio_idfolios"),
		@JoinColumn(name="vendedor_clave", referencedColumnName="vendedor_clave")
		})
	private BloqueFolio bloqueFolio;

	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumn(name="escuela_clave",referencedColumnName="clave")
	private Escuela escuela;

	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumn(name="idprofesor",referencedColumnName="idprofesor")
	private Profesor profesor;
	
	@OneToMany(mappedBy="pedidos",cascade=CascadeType.ALL)
	private List<HistorialVenta> pedidos;

	public Venta(){
	}

	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public float getComisionDirector() {
		return this.comisionDirector;
	}

	public void setComisionDirector(float comisionDirector) {
		this.comisionDirector = comisionDirector;
	}

	public float getComisionProfesor() {
		return this.comisionProfesor;
	}

	public void setComisionProfesor(float comisionProfesor) {
		this.comisionProfesor = comisionProfesor;
	}

	public float getComisionVendedor() {
		return this.comisionVendedor;
	}

	public void setComisionVendedor(float comisionVendedor) {
		this.comisionVendedor = comisionVendedor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BloqueFolio getBloqueFolio() {
		return this.bloqueFolio;
	}

	public void setBloqueFolio(BloqueFolio bloqueFolio) {
		this.bloqueFolio = bloqueFolio;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@JsonIgnore
	public List<HistorialVenta> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<HistorialVenta> pedidos) {
		this.pedidos = pedidos;
	}
	
	@PreRemove
	public void Nullable(){
		for (HistorialVenta pedido: pedidos) {
			pedido=null;
		}
	}
	
}
