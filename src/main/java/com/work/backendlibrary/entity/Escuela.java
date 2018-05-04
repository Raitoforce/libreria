package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="escuela")
public class Escuela implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonView(VentaView.interno.class)
	@Id
	@Column(name="clave")
	private String clave;
	
	@JsonView(VentaView.interno.class)
	@Column(name="nombre")
	private String nombre;
	@Column(name="codigo_postal")
	private String codigoPostal;
	@Column(name="direccion")
	private String direccion;
	@Column(name="colonia")
	private String colonia;
	@Column(name="email")
	private String email;
	@Column(name="estado")
	private String estado;
	@Column(name="municipio")
	private String municipio;
	@Column(name="telefono")
	private String telefono;
	@Column(name="turno")
	private String turno;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="director_iddirector", insertable=true,updatable=true)
	private Director director;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="zona_idzona")
	private Zona zona;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="escuela_has_profesor", joinColumns={
				  @JoinColumn(name = "escuela_clave", referencedColumnName="clave",nullable = false,updatable = false)
			},
			inverseJoinColumns={@JoinColumn(name="profesor_idprofesor",nullable = false,updatable = false)}
			)
	private List<Profesor> profesores;

	public Escuela() {
	}

	public Escuela(String clave, String nombre, String codigoPostal, String direccion, String colonia, String email, String estado, String municipio, String telefono, String turno, Director director, List<Profesor> profesores) {
		this.clave = clave;
		this.nombre = nombre;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.colonia = colonia;
		this.email = email;
		this.estado = estado;
		this.municipio = municipio;
		this.telefono = telefono;
		this.turno = turno;
		this.director = director;
		this.profesores = profesores;
	}
	
//	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="idprofesor")
	@JsonIgnoreProperties({"escuelas"})
	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Transactional
	@PreRemove
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	public  void deleteEscuela(){
		for (Profesor profesor: getProfesores()) {
			profesor=null;
		}
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
}