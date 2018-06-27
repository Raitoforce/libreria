package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.entity.BloqueFolio;
import com.work.backendlibrary.service.BloqueFolioService;

@RestController
@RequestMapping("/bloque_folio")
public class BloqueFolioController {
	@Autowired
	@Qualifier("bloqueFolioService")
	BloqueFolioService bfservice;
	
	@GetMapping("")
	public ResponseEntity<List<BloqueFolio>> devolverBloqueFolio(){
		List<BloqueFolio> bfolios = bfservice.listAllBloqueFolios();
		return new ResponseEntity<List<BloqueFolio>>(bfolios,HttpStatus.OK);
	}
	
	@GetMapping("/consultar")
	public ResponseEntity<BloqueFolio> consultaBloqueFolio(@RequestParam("clave") String clave,@RequestParam("id") int id){
		BloqueFolio bfolio=bfservice.consultarFolio(clave,id);
		return new ResponseEntity<BloqueFolio>(bfolio,HttpStatus.OK);
	}
	
	@GetMapping("/vendedortemporada")
	public ResponseEntity<BloqueFolio> consultarByVendedorAndTemporada(@RequestParam("clave")String clave,@RequestParam("idtemporada")Integer idtemporada){
		BloqueFolio bfolios=bfservice.consultarByVendedorAndTemporada(clave, idtemporada);
		return new ResponseEntity<BloqueFolio>(bfolios,HttpStatus.OK);
	}
	
	@GetMapping("/range")
	public boolean rangoFolio(@RequestParam("valor")int valor,@RequestParam("idfolio")Integer idfolio){
		return bfservice.isInRange(valor,idfolio);
	}
	
	@GetMapping("/isValidFolio")
	public boolean isValidFolio(@RequestParam("clave")String clave,@RequestParam("valor")Integer valor,@RequestParam("idtemporada")int idtemporada){
		return bfservice.isAValidFolio(clave, valor,idtemporada);
	}
	
	@GetMapping("/isValidFolioType")
	public boolean isValidFolioType(@RequestParam("clave")String clave,@RequestParam("valor")Integer valor,@RequestParam("type")String type,@RequestParam("idtemporada")int idtemporada){
		return bfservice.isAValidFolioType(clave,valor, type,idtemporada);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarBloqueFolio(@RequestBody BloqueFolio bfolio){
		bfservice.addBloqueFolio(bfolio);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="")
	public ResponseEntity<String> eliminarBloqueFolio(@RequestParam("clave") String clave,@RequestParam("id") int id){
		bfservice.removeBloqueFolio(clave,id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarBloqueFolio(@RequestBody BloqueFolio bfolio){
		bfservice.updateFolio(bfolio);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
