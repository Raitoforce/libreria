package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.model.LibroStockModel;

public interface LibroService {
	List<Libro> listAllLibros();
	Libro addLibro(Libro libro);
	void removeLibro(String clave_producto);
	Libro updateLibro(Libro libro);
	Libro consultarLibro(String clave_producto);
	List<Libro> listPage(Pageable pageable);
	boolean LibroIsOnDB(String clave_producto);
}
