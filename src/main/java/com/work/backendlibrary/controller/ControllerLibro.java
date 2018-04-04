package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
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

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.service.LibroService;

@RestController
@RequestMapping("/productos")
public class ControllerLibro{
	@Autowired
	@Qualifier("libroServiceImpl")
	LibroService libroService;
	
	@GetMapping("")
	public ResponseEntity<List<Libro>> devolverLibros(){
		List<Libro> libros = libroService.listAllLibros();
		return new ResponseEntity<List<Libro>>(libros,HttpStatus.OK);
	}
	
	@GetMapping("/pagina")
    public ResponseEntity<List<Libro>> listAllpage(Pageable pageable) {
        List<Libro> libros = libroService.listPage(pageable);
        if (libros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
	
	@GetMapping("/{clave}")
	public ResponseEntity<Libro> consultaLibro(@PathVariable("clave") String isbn){
		Libro libro=libroService.consultarLibro(isbn);
		return new ResponseEntity<Libro>(libro,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarLibro(@RequestBody Libro libro){
		libroService.addLibro(libro);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{clave}")
	public ResponseEntity<String> eliminarLibro(@PathVariable("clave") String isbn){
		libroService.removeLibro(isbn);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarLibro(@RequestBody Libro libro){
		libroService.updateLibro(libro);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
