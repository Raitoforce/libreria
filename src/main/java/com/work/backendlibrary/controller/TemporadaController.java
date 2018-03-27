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

import com.work.backendlibrary.entity.Temporada;
import com.work.backendlibrary.service.TemporadaService;

@RestController
@RequestMapping("/Temporada")
public class TemporadaController {
	@Autowired
	@Qualifier("temporadaServiceImpl")
	TemporadaService tmps;
	
	@GetMapping("")
	public ResponseEntity<List<Temporada>> devolverTemporadas(){
		List<Temporada> temporadas=tmps.listAllTemporadas();
		return new ResponseEntity<List<Temporada>>(temporadas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temporada> consultarTemporada(@PathVariable("id") int id){
		Temporada temporada= tmps.consultarTemporada(id);
		return new ResponseEntity<Temporada>(temporada,HttpStatus.OK);
		
	}
	
	@PostMapping(path="",consumes="application/json")
	public ResponseEntity<String> insertarTemporada(@RequestBody Temporada temporada){
		tmps.addTemporada(temporada);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarTemporada(@RequestBody Temporada temporada){
		tmps.updateTemporada(temporada);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarDirector(@PathVariable("id")int id){
		tmps.removeTemporada(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
