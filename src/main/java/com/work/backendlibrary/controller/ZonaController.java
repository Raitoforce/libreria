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

import com.work.backendlibrary.entity.Zona;
import com.work.backendlibrary.service.ZonaService;

@RestController
@RequestMapping("/zona")
public class ZonaController{
	@Autowired
	@Qualifier("zonaService")
	ZonaService zonaService;
	
	@GetMapping("")
	public ResponseEntity<List<Zona>> devolverZona(){
		List<Zona> zonas = zonaService.listAllZonas(); 
		return new ResponseEntity<List<Zona>>(zonas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Zona> consultaZona(@PathVariable("id") String id){
		Zona zona= zonaService.consultarZona(id);
		return new ResponseEntity<Zona>(zona,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarZona(@RequestBody Zona zona){
		zonaService.addZona(zona);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarZona(@PathVariable("id") String id){
		zonaService.removeZona(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarZona(@RequestBody Zona zona){
		zonaService.updateZona(zona);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/zona={zona}")
    public ResponseEntity<Boolean> buscarXZona(@PathVariable String zona) {
        Boolean exist = zonaService.ZonaIsOnDB(zona);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }

	
}
