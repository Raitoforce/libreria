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

import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;
import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;
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
	
	
	@PostMapping(path="/abonarVendedor",consumes="application/json")
	public ResponseEntity<String> abonarVendedor(@RequestBody ComisionModel comision){
		comisionService.addComisionVendedor(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping(path="/abonarDirector",consumes="application/json")
	public ResponseEntity<String> abonarDirector(@RequestBody ComisionModel comision){
		comisionService.addComisionDirector(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/*@PostMapping(path="/abonarLider",consumes="application/json")
	public ResponseEntity<String> abonarLider(@RequestBody ComisionModel comision){
		comisionService.addComisionLider(comision);
		return new ResponseEntity<String>(HttpStatus.OK);
	}*/
	
	@GetMapping("/comisionVendedor")
	public ResponseEntity<List<ComisionesVistaModel>> getComisionesVendedor(@RequestParam int idtemporada){
		List<ComisionesVistaModel> comisiones = comisionService.consultarComisionesByVendedors(idtemporada); 
		return new ResponseEntity<List<ComisionesVistaModel>>(comisiones,HttpStatus.OK);
	}
	
	@GetMapping("/comisionDirector")
	public ResponseEntity<List<ComisionesVistaModel>> getComisionesDirector(@RequestParam int idtemporada){
		List<ComisionesVistaModel> comisiones = comisionService.consultarComisionesByDirectors(idtemporada);
		return new ResponseEntity<List<ComisionesVistaModel>>(comisiones,HttpStatus.OK);
	}
	
	@GetMapping("/comisionLider")
	public ResponseEntity<List<ComisionesVistaModel>> getComisionesLider(@RequestParam int idtemporada){
		List<ComisionesVistaModel> comisiones = comisionService.consultarComisionesByLideres(idtemporada);
		return new ResponseEntity<List<ComisionesVistaModel>>(comisiones,HttpStatus.OK);
	}
	
	@JsonView(VentaView.comision.class)
	@GetMapping("/comisionesVendedor")
	public ResponseEntity<List<Comision>> getComisionesHVendedor(@RequestParam int idtemporada,@RequestParam String clave){
		List<Comision> comisiones = comisionService.consultarHistorialComisionesByVendedors(idtemporada, clave);
		return new ResponseEntity<List<Comision>>(comisiones,HttpStatus.OK);
	}
	
	@GetMapping("/comisionesDirector")
	public ResponseEntity<List<Comision>> getComisionesHDirector(@RequestParam int idtemporada,@RequestParam int iddirector){
		List<Comision> comisiones = comisionService.consultarHistorialComisionesByDirectors(idtemporada, iddirector);
		return new ResponseEntity<List<Comision>>(comisiones,HttpStatus.OK);
	}
	
	@GetMapping("/comisionesLider")
	public ResponseEntity<List<Comision>> getComisionesHLider(@RequestParam int idtemporada,@RequestParam int idlider){
		List<Comision> comisiones = comisionService.consultarHistorialComisionesByLideres(idtemporada, idlider);
		return new ResponseEntity<List<Comision>>(comisiones,HttpStatus.OK);
	}
	
}
