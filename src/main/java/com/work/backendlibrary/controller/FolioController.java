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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.entity.Folio;
import com.work.backendlibrary.service.FolioService;


@RestController
@RequestMapping("/Folio")
public class FolioController {
	@Autowired
	@Qualifier("folioServiceImpl")
	FolioService fs;
	
	@GetMapping("")
	public ResponseEntity<List<Folio>> devolverFolios(){
		List<Folio> folios = fs.listAllFolios();
		return new ResponseEntity<List<Folio>>(folios,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Folio> consultaFolio(@PathVariable("id") int id){
		Folio folio=fs.consultarFolio(id);
		return new ResponseEntity<Folio>(folio,HttpStatus.OK);
	}
	
	@GetMapping("/range")
	public boolean rangoFolio(@RequestParam("valor")int valor,@RequestParam("tipo")String tipo){
		return fs.isInRange(valor,tipo);
	}
	
	@GetMapping("/Temporada/{id}")
	public ResponseEntity<List<Folio>> consultaTemporada(@PathVariable("id") int id){
		List<Folio> folios=fs.consultarTemporada(id);
		return new ResponseEntity<List<Folio>>(folios,HttpStatus.OK);
	}
	
	@PostMapping(path="",consumes="application/json")
	public ResponseEntity<String> insertarFolio(@RequestBody Folio folio){
		fs.addFolio(folio);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarFolio(@PathVariable("id") int id){
		fs.removeFolio(id);;
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarFolio(@RequestBody Folio folio){
		fs.updateFolio(folio);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
