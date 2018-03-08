package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.model.LibroModel;
import com.work.backendlibrary.service.LibroService;

@RestController
@RequestMapping("/Libro")
public class ControllerLibro{
	@Autowired
	@Qualifier("libroServiceImpl")
	LibroService libroService;
	
	@GetMapping("/Libros")
	public ResponseEntity<List<LibroModel>> devolverLibros(){
		List<LibroModel> libros = libroService.listAllLibros();
		return new ResponseEntity<List<LibroModel>>(libros,HttpStatus.OK);
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<LibroModel> consultaLibro(@PathVariable("isbn") String isbn){
		LibroModel librom=libroService.consultarLibro(isbn);
		return new ResponseEntity<LibroModel>(librom,HttpStatus.OK);
	}
	
	@PostMapping(path="",consumes="application/json")
	public ResponseEntity<String> insertarLibro(@RequestBody LibroModel librom){
		libroService.addLibro(librom);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{isbn}")
	public ResponseEntity<String> eliminarLibro(@PathVariable("isbn") String isbn){
		libroService.removeLibro(isbn);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarLibro(@RequestBody LibroModel librom){
		libroService.updateLibro(librom);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
