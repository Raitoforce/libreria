package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.model.LibroModel;

@Component("libroConverter")
public class LibroConverter {
	
	public LibroModel entity2model(Libro libro){
		LibroModel librom=new LibroModel();
		librom.setIsbn(libro.getIsbn());
		librom.setTitulo(libro.getTitulo());
		librom.setAutor(libro.getAutor());
		librom.setDescripcion(libro.getDescripcion());
		librom.setEditorial(libro.getEditorial());
		librom.setCurso(libro.getCurso());
		librom.setPrecio(libro.getPrecio());
		librom.setCosto(libro.getCosto());
		librom.setCantidad(libro.getCantidad());
		librom.setAño(libro.getAño());
		
		return librom;
	}
	
	public Libro model2entity(LibroModel librom){
		Libro libro=new Libro();
		libro.setIsbn(librom.getIsbn());
		libro.setTitulo(librom.getTitulo());
		libro.setAutor(librom.getAutor());
		libro.setDescripcion(librom.getDescripcion());
		libro.setEditorial(librom.getEditorial());
		libro.setCurso(librom.getCurso());
		libro.setPrecio(librom.getPrecio());
		libro.setCosto(librom.getCosto());
		libro.setCantidad(librom.getCantidad());
		libro.setAño(librom.getAño());
		
		return libro;
	}
	
	public List<LibroModel> listEntity2listmodel(List<Libro> libros){
		List<LibroModel> list = new ArrayList<LibroModel>();
		LibroModel librom;
		for(Libro libro: libros){
			librom=entity2model(libro);
			list.add(librom);
		}
		
		return list;
	}
}
