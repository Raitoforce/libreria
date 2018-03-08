package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.LibroConverter;
import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.model.LibroModel;
import com.work.backendlibrary.repository.LibroJPARepository;
import com.work.backendlibrary.service.LibroService;

@Service("libroServiceImpl")
public class LibroServiceImpl implements LibroService{
	
	@Autowired
	@Qualifier("libroJPARepository")
	private LibroJPARepository libroJPARepository;
	
	@Autowired
	@Qualifier("libroConverter")
	private LibroConverter lbc;
	
	@Override
	public List<LibroModel> listAllLibros() {
		return lbc.listEntity2listmodel(libroJPARepository.findAll());
	}

	@Override
	public Libro addLibro(LibroModel librom) {
		Libro libro=lbc.model2entity(librom);
		return libroJPARepository.save(libro);
	}

	@Override
	public void removeLibro(String isbn) {
		//libroJPARepository.deleteByIsbn(isbn);
		libroJPARepository.deleteById(isbn);
	}

	@Override
	public Libro updateLibro(LibroModel librom){
		Libro libro=lbc.model2entity(librom);
		return libroJPARepository.save(libro);
	}
	
	@Override
	public LibroModel consultarLibro(String isbn){
		Libro libro = libroJPARepository.findByIsbn(isbn);
		return lbc.entity2model(libro);
	}
}
