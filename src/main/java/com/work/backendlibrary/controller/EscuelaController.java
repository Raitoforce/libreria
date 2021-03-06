package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
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

import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.model.EscuelaModel;
import com.work.backendlibrary.service.EscuelaService;


@RestController
@RequestMapping("/escuelas")
public class EscuelaController {
	@Autowired
	@Qualifier("escuelaServiceImpl")
	EscuelaService service;
	
	@GetMapping("")
	public ResponseEntity<List<Escuela>> devolverEscuelas(){
		List<Escuela> escuelas = service.listAllEscuelas();
		return new ResponseEntity<List<Escuela>>(escuelas,HttpStatus.OK);
	}
	
	@GetMapping("/pagina")
    public ResponseEntity<List<EscuelaModel>> listAllpage(Pageable pageable) {
        List<EscuelaModel> escuelas = service.listpage(pageable);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }
	
	@GetMapping("/{clave}")
	public ResponseEntity<Escuela> consultaEscuela(@PathVariable("clave") String clave){
		Escuela escuela=service.consultarEscuela(clave);
		return new ResponseEntity<Escuela>(escuela,HttpStatus.OK);
	}
	
	@GetMapping("/director/{id}")
	public ResponseEntity<List<Escuela>> consultaDirector(@PathVariable("id") int id){
		List<Escuela> escuelas=service.consultarDirector(id);
		return new ResponseEntity<List<Escuela>>(escuelas,HttpStatus.OK);
	}
	
	@GetMapping("/zona={zona}")
	public ResponseEntity<List<Escuela>> consultaByZona(@PathVariable("zona")String zona){
		List<Escuela> escuelas=service.findByZona(zona);
		return new ResponseEntity<List<Escuela>>(escuelas,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertar(@RequestBody Escuela escuela){
		service.addEscuela(escuela);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{clave}")
	public ResponseEntity<String> eliminarLibro(@PathVariable("clave") String clave){
		service.removeEscuela(clave);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarLibro(@RequestBody Escuela escuela){
		service.updateEscuela(escuela);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/clave={clave}")
    public ResponseEntity<Boolean> buscarXClave(@PathVariable String clave) {
        Boolean exist = service.EscuelaIsOnDB(clave);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }
}
