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

import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.service.HistorialVentaService;

@RestController
@RequestMapping("/historial_venta")
public class HistorialVentaController {
	@Autowired
	@Qualifier("historialVentaService")
	HistorialVentaService hvService;
	
	@JsonView(VentaView.interno.class)
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
	
	@GetMapping("/venta={id}")
	public ResponseEntity<List<HistorialVenta>> consultabyVenta(@PathVariable("id") String id){
		List<HistorialVenta> hventas= hvService.consultarByVenta(id);
		return new ResponseEntity<List<HistorialVenta>>(hventas,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarHistorialVenta(@RequestBody HistorialVentaModel hventa){
		hvService.addHistorialVenta(hventa);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarHistorialVenta(@PathVariable("id") int id){
		hvService.removeHistorialVenta(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarHistorialVenta(@RequestBody HistorialVentaModel hventa){
		hvService.updateHistorialVenta(hventa);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
