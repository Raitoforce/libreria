package com.work.backendlibrary.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;
import org.hibernate.annotations.Cascade;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "libro")
public class Libro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView(VentaView.interno.class)
	@Id
	@Column(name="clave_producto")
	private String claveProducto;
	
	@Column(name="isbn")
	private String isbn;
	
	@JsonView(VentaView.Todo.class)
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
	
	@JsonView(VentaView.Todo.class)
	@Column(name="nivel",nullable=true)
	private String nivel;
	
	@JsonView(VentaView.Todo.class)
	@Column(name="curso",nullable=true)
	private String curso;
	
	@JsonView(VentaView.Todo.class)
	@Column(name="precio",nullable=true)
	private Float precio;
	
	@JsonView(VentaView.Todo.class)
	@Column(name="costo",nullable=true)
	private Float costo;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "libro")
	private List<Stock> stocks;

	public Libro() {
	}

	public Libro(String clave_producto, String isbn, String titulo, String descripcion, String autor, String editorial, String year, String nivel, String curso, Float precio, Float costo, Integer cantidad) {
		this.claveProducto = clave_producto;
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
	
	@JsonIgnore
	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Transactional
	@PreRemove
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	public  void Nullable(){
		for (Stock stock: stocks) {
			stock=null;
		}
	}

}
