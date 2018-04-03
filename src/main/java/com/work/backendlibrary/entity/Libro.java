package com.work.backendlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro{
	
	@Id
	@Column(name="clave_producto")
	private String claveProducto;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="titulo",nullable=true)
	private String titulo;
	@Column(name="descripcion",nullable=true)
	private String descripcion;
	@Column(name="autor",nullable=true)
	private String autor;
	@Column(name="editorial",nullable=true)
	private String editorial;
	@Column(name="year",nullable=true)
	private String year;
	@Column(name="nivel",nullable=true)
	private String nivel;
	@Column(name="curso",nullable=true)
	private String curso;
	@Column(name="precio",nullable=true)
	private Float precio;
	@Column(name="costo",nullable=true)
	private Float costo;
	@Column(name="cantidad",nullable=true)
	private Integer cantidad;

	public Libro() {
	}

	public Libro(String claveProducto, String isbn, String titulo, String descripcion, String autor, String editorial, String year, String nivel, String curso, Float precio, Float costo, Integer cantidad) {
		this.claveProducto = claveProducto;
		this.isbn = isbn;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.autor = autor;
		this.editorial = editorial;
		this.year = year;
		this.nivel = nivel;
		this.curso = curso;
		this.precio = precio;
		this.costo = costo;
		this.cantidad = cantidad;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getyear() {
		return year;
	}

	public void setyear(String year) {
		this.year = year;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getClave_producto() {
		return claveProducto;
	}

	public void setClave_producto(String clave_producto) {
		this.claveProducto = clave_producto;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
