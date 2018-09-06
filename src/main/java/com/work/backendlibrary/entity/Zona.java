package com.work.backendlibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ListIndexBase;


@Entity
@Table(name="zona")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idzona")
	private String idzona;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="vendedor_clave",referencedColumnName="clave")
	private Vendedor vendedor;

	@JsonIgnore
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	Set<Escuela> escuelas;


	@JsonIgnoreProperties({"bloqueFolios","zonas"})
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Zona(){
	}
	
	public String getIdzona() {
		return this.idzona;
	}

	public void setIdzona(String idzona) {
		this.idzona = idzona;
	}

	
	public Set<Escuela> getEscuelas() {
		return escuelas;
	}

	public void setEscuelas(Set<Escuela> escuelas) {
		this.escuelas = escuelas;
	}

	@PreRemove
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	public void Nullable(){
		for (Escuela escuela: escuelas) {
			escuela.setZona(null);
		}
	}

}