package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.model.LibroModel;

public interface LibroService {
	public abstract List<LibroModel> listAllLibros();
	public abstract Libro addLibro(LibroModel librom);
	public abstract void removeLibro(String isbn);
	public abstract Libro updateLibro(LibroModel librom);
	public abstract LibroModel consultarLibro(String isbn);
}
