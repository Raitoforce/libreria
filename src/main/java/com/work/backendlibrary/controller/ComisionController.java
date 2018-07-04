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

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.service.ComisionService;

@RestController
@RequestMapping("/comision")
public class ComisionController {

	@Autowired
	@Qualifier("comisionService")
	ComisionService comisionService;
	
	@GetMapping("")
	public ResponseEntity<List<Comision>> devolverComisiones(){
		List<Comision> comisiones = comisionService.listAllComisiones(); 
		return new ResponseEntity<List<Comision>>(comisiones,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comision> consultaComision(@PathVariable("id") int id){
		Comision comision= comisionService.consultarComision(id);
		return new ResponseEntity<Comision>(comision,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarComision(@RequestBody Comision comision){
		comisionService.addComision(comision);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarComision(@PathVariable("id") int id){
		comisionService.removeComision(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarComision(@RequestBody Comision comision){
		comisionService.updateComision(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> abonarVendedor(@RequestBody Comision comision){
		comisionService.updateComision(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> abonarDirector(@RequestBody Comision comision){
		comisionService.updateComision(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	

}
