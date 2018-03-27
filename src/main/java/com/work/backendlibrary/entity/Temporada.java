package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="temporada")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //Anotacion para manejar el lazy
public class Temporada implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtemporada")
	private int idtemporada;
	@Column(name="nombre")
	private String nombre;
	@Column(name="fecha_inicio")
	private Date fecha_inicio;
	@Column(name="fecha_termino")
	private Date fecha_termino;
	
	@OneToMany(mappedBy="idtemporada",cascade=CascadeType.ALL)
	List<Folio> folios;
	
	public int getIdtemporada() {
		return idtemporada;
	}
	public void setIdtemporada(int idtemporada) {
		this.idtemporada = idtemporada;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_termino() {
		return fecha_termino;
	}
	public void setFecha_termino(Date fecha_termino) {
		this.fecha_termino = fecha_termino;
	}
	
	
	public Temporada(int idtemporada, String nombre, Date fecha_inicio, Date fecha_termino /*,List<Folio> folios*/) {
		super();
		this.idtemporada = idtemporada;
		this.nombre = nombre;
		this.fecha_inicio = fecha_inicio;
		this.fecha_termino = fecha_termino;
		//this.folios = folios;
	}
	
	/*public List<Folio> getFolios() {
		return folios;
	}
	public void setFolios(List<Folio> folios) {
		this.folios = folios;
	}*/
	
	public Temporada(){}
	
	
}
