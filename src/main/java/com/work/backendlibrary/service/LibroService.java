package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Libro;

public interface LibroService {
	public abstract List<Libro> listAllLibros();
	public abstract Libro addLibro(Libro libro);
	public abstract void removeLibro(String clave_producto);
	public abstract Libro updateLibro(Libro libro);
	public abstract Libro consultarLibro(String clave_producto);
	public abstract List<Libro> listPage(Pageable pageable);
	
	public abstract boolean LibroIsOnDB(String clave_producto);
}
