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

import com.work.backendlibrary.model.ProfesorModel;
import com.work.backendlibrary.service.ProfesorService;

@RestController()
@RequestMapping("/Profesor")
public class ControllerProfesor {
	@Autowired
	@Qualifier("profesorServiceImpl")
	private ProfesorService profesorService;
	
	@GetMapping("")
	public ResponseEntity<List<ProfesorModel>> devolverDirectores(){
		List<ProfesorModel> profesores=profesorService.listAllProfesores();
		return new ResponseEntity<List<ProfesorModel>>(profesores,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfesorModel> consultarDirector(@PathVariable("id") int id){
		ProfesorModel profesorm= profesorService.consultarProfesor(id);
		return new ResponseEntity<ProfesorModel>(profesorm,HttpStatus.OK);
		
	}
	
	@PostMapping(path="",consumes="application/json")
	public ResponseEntity<String> insertarDirector(@RequestBody ProfesorModel profesorm){
		profesorService.addProfesor(profesorm);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarDirector(@RequestBody ProfesorModel profesorm){
		profesorService.updateProfesor(profesorm);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarDirector(@PathVariable("id")int id){
		profesorService.removeProfesor(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
}
