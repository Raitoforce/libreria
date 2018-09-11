package com.work.backendlibrary.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.model.VentaModel;
import com.work.backendlibrary.service.HistorialVentaService;
import com.work.backendlibrary.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	@Qualifier("ventaService")
	VentaService ventaService;
	
	@Autowired
	@Qualifier("historialVentaService")
	HistorialVentaService hvService;
	
	@JsonView(VentaView.interno.class)
	@GetMapping("")
	public ResponseEntity<List<Venta>> devolverVenta(){
		List<Venta> ventas = ventaService.listAllVentas(); 
		return new ResponseEntity<List<Venta>>(ventas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> consultaVenta(@PathVariable("id") String id){
		Venta venta= ventaService.consultarVenta(id);
		return new ResponseEntity<Venta>(venta,HttpStatus.OK);
	}
	
	@PostMapping("resurtido")
	public ResponseEntity<String> resurtidos(@RequestParam("folio") String folio,@RequestBody List<HistorialVentaModel> pedidos){
		LinkedHashSet<HistorialVentaModel> pedidosN = new LinkedHashSet<>(pedidos);
		int num=hvService.getMaximo(folio)+1;
		for (HistorialVentaModel pedido : pedidosN) {
			pedido.setNumresurtido(num);
		}
		Venta venta=ventaService.appendPedidos(folio, pedidosN);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarVenta(@RequestBody VentaModel venta){
		venta.setNumResurtido(0);
		ventaService.addVenta(venta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarVenta(@PathVariable("id") String id){
		if(!ventaService.VentaHasConfirmed(id)){
			ventaService.removeVenta(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarVenta(@RequestBody VentaModel venta){
		ventaService.updateVenta(venta);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/folio={folio}")
    public ResponseEntity<Boolean> buscarXFolio(@PathVariable String folio) {
        Boolean exist = ventaService.VentaIsOnDB(folio);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }
	
}
