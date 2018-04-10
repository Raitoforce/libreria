package com.work.backendlibrary.entity;

import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "profesor")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprofesor")
	private int idprofesor;
	@NotNull
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	@NotNull
	@Basic(optional = false)
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "telefono")
	private String telefono;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="escuela_has_profesor", joinColumns={
			@JoinColumn(name="profesor_idprofesor",nullable = false,updatable = false)
	},
			inverseJoinColumns={@JoinColumn(name = "escuela_clave", referencedColumnName="clave",nullable = false,updatable = false)}
	)
	List<Escuela> escuelas;
	
	// private String email;
	public int getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

//	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="clave")
	@JsonIgnoreProperties({"profesores"})
	public List<Escuela> getEscuelas() {
		return escuelas;
	}

	public void setEscuelas(List<Escuela> escuelas) {
		this.escuelas = escuelas;
	}

	public Profesor() {
	}

	public Profesor(int idprofesor, @NotNull String nombre, @NotNull String apellidos, String telefono) {
		super();
		this.idprofesor = idprofesor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}

	@Transactional
	@PreRemove
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	public void DeleteProfesor(){
		for (Escuela escuela: getEscuelas()
				) {
			escuela=null;
		}
	}

}
