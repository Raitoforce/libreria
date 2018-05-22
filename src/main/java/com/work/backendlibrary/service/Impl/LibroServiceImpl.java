package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.repository.LibroJPARepository;
import com.work.backendlibrary.service.LibroService;

@Service("libroServiceImpl")
public class LibroServiceImpl implements LibroService{
	
	@Autowired
	@Qualifier("libroJPARepository")
	private LibroJPARepository libroJPARepository;
	
	@Override
	public List<Libro> listAllLibros() {
		return libroJPARepository.findAll();
	}

	@Override
	public Libro addLibro(Libro libro) {
		//Libro libro=lbc.model2entity(librom);
		return libroJPARepository.save(libro);
	}

	@Override
	public void removeLibro(String clave_producto) {
		//libroJPARepository.deleteByIsbn(clave_producto);
		libroJPARepository.deleteById(clave_producto);
	}

	@Override
	public Libro updateLibro(Libro libro){
		//Libro libro=lbc.model2entity(librom);
		return libroJPARepository.save(libro);
	}
	
	@Override
	public Libro consultarLibro(String clave_producto){
		Libro libro = libroJPARepository.findByClaveProducto(clave_producto);
		return libro;
	}

	@Override
	public List<Libro> listPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return libroJPARepository.findAll(pageable).getContent();
	}

	@Override
	public boolean LibroIsOnDB(String clave_producto) {
		if(libroJPARepository.findByClaveProducto(clave_producto)!=null)
			return true;
		return false;
	}
	
	
}
