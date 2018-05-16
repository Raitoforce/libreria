package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController{
	@Autowired
	@Qualifier("inventarioService")
	InventarioService inventarioService;
	
	@GetMapping("")
	public ResponseEntity<List<HistorialVenta>> devolverInventario(){
		List<HistorialVenta> inventario=inventarioService.getPedidosPendientes();
		return new ResponseEntity<>(inventario,HttpStatus.OK);
	}
	
	@GetMapping("/confirmar")
	public ResponseEntity<String> confirmarPedido(@RequestParam("idHistorial")int idHistorial,@RequestParam("entregados")int entregados){
		inventarioService.confirmarPedido(idHistorial, entregados);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<String> crearPDF(@RequestParam("folio")String folio){
		inventarioService.generarReporte(folio);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
