package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="escuela")
public class Escuela implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="clave")
	private String clave;

	@Column(name="nombre")
	private String nombre;
	@Column(name="codigo_postal")
	private String codigoPostal;
	@Column(name="direccion")
	private String direccion;
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
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="director_iddirector"/*,insertable=false,updatable=false*/)
	private Director director;

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinTable(name="escuela_has_profesor", joinColumns={
				  @JoinColumn(name = "escuela_clave", referencedColumnName="clave")  
			},
			inverseJoinColumns={@JoinColumn(name="profesor_idprofesor")}
			)
	private List<Profesor> profesores;

	public Escuela() {
	}
	
	
	public Escuela(String clave, String nombre, String codigoPostal, String direccion, String email, String estado,
			String municipio, String telefono, String turno, Director director, List<Profesor> profesores) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
		this.municipio = municipio;
		this.telefono = telefono;
		this.turno = turno;
		this.director = director;
		this.profesores = profesores;
	}



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

	
}