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

import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;
import com.work.backendlibrary.service.ProfesorService;

@RestController()
@RequestMapping("/profesores")
public class ProfesorController {
	@Autowired
	@Qualifier("profesorServiceImpl")
	private ProfesorService profesorService;
	
	@GetMapping("")
	public ResponseEntity<List<Profesor>> devolverProfesores(){
		List<Profesor> profesores = profesorService.listAllProfesores();
		return new ResponseEntity<List<Profesor>>(profesores,HttpStatus.OK);
	}
	
	@GetMapping("/pagina")
    public ResponseEntity<List<ProfesorModel>> listAllpage(Pageable pageable) {
        List<ProfesorModel> profesores = profesorService.listPage(pageable);
        if (profesores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Profesor> consultarProfesor(@PathVariable("id") int id){
		Profesor profesorm = profesorService.consultarProfesor(id);
		return new ResponseEntity<Profesor>(profesorm,HttpStatus.OK);
		
	}
	
	@GetMapping("/escuela={clave}")
	public ResponseEntity<List<Profesor>> consultarByEscuela(@PathVariable("escuela")String clave){
		List<Profesor> profesors=profesorService.findByEscuela(clave);
		return new ResponseEntity<>(profesors,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<Profesor> insertarProfesor(@RequestBody Profesor profesor){
		Profesor profesorResponse = profesorService.addProfesor(profesor);
		return new ResponseEntity<Profesor>(profesorResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<Profesor> actualizarProfesor(@RequestBody Profesor profesor){
		Profesor profesorResponse = profesorService.updateProfesor(profesor);
		return new ResponseEntity<Profesor>(profesorResponse,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarDirector(@PathVariable("id")int id){
		profesorService.removeProfesor(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
}
