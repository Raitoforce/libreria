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

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.service.HistorialVentaService;

@RestController
@RequestMapping("/historial_venta")
public class HistorialVentaController {
	@Autowired
	@Qualifier("historialVentaService")
	HistorialVentaService hvService;
	
	@GetMapping("")
	public ResponseEntity<List<HistorialVenta>> devolverHistorialVenta(){
		List<HistorialVenta> hventas = hvService.listAllHistorialVentas(); 
		return new ResponseEntity<List<HistorialVenta>>(hventas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HistorialVenta> consultaHistorialVenta(@PathVariable("id") int id){
		HistorialVenta hventa= hvService.consultarHistorialVenta(id);
		return new ResponseEntity<HistorialVenta>(hventa,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarHistorialVenta(@RequestBody HistorialVenta hventa){
		hvService.addHistorialVenta(hventa);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarHistorialVenta(@PathVariable("id") int id){
		hvService.removeHistorialVenta(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarHistorialVenta(@RequestBody HistorialVenta hventa){
		hvService.updateHistorialVenta(hventa);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
