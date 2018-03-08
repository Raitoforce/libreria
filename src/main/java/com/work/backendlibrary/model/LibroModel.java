package com.work.backendlibrary.model;

public class LibroModel {
	private String isbn;
	private String titulo;
	private String descripcion;

	private String autor;
	private String editorial;
	private String año;
	private String curso;
	private Float precio;
	private Float costo;

	public LibroModel() {
	}

	public LibroModel(String isbn, String titulo, String descripcion, String autor, String editorial, String año,
			String curso, Float precio, Float costo, Integer cantidad) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.autor = autor;
		this.editorial = editorial;
		this.año = año;
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

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
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

	private Integer cantidad;
}
