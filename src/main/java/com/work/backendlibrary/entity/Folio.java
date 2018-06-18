package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="folio")
public class Folio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idfolios")
	private Integer idfolios;
	
	@Column(name="tipo")
	private String tipo;
	@Column(name="inicio")
	private Integer inicio;
	@Column(name="fin")
	private Integer fin;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinColumn(name="temporada_idtemporada")
	private Temporada idtemporada;
	
	@OneToMany(mappedBy="folio")
	private List<BloqueFolio> bloqueFolios;
	
	
	public int getIdfolios() {
		return idfolios;
	}
	public void setIdfolios(int idfolios) {
		this.idfolios = idfolios;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public Integer getFin() {
		return fin;
	}
	public void setFin(Integer fin) {
		this.fin = fin;
	}
	
	public Temporada getIdtemporada() {
		return idtemporada;
	}
	public void setIdtemporada(Temporada idtemporada) {
		this.idtemporada = idtemporada;
	}
	
	public Folio(int idfolios, String tipo, Integer inicio, Integer fin, Temporada idtemporada) {
		super();
		this.idfolios = idfolios;
		this.tipo = tipo;
		this.inicio = inicio;
		this.fin = fin;
		this.idtemporada = idtemporada;
	}
	
	public Folio(){}
	
}
