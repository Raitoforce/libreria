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

import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.model.DirectorModel;
import com.work.backendlibrary.service.DirectorService;

@RestController
@RequestMapping("/Director")
public class ControllerDirector {
	@Autowired
	@Qualifier("directorServiceImpl")
	private DirectorService directorService;
	
	@GetMapping("")
	public ResponseEntity<List<DirectorModel>> devolverDirectores(){
		List<DirectorModel> directores=directorService.listAllDirectores();
		return new ResponseEntity<List<DirectorModel>>(directores,HttpStatus.OK);
	}
	
	@GetMapping("/pagina")
    public ResponseEntity<List<DirectorModel>> listAllpage(Pageable pageable) {
        List<DirectorModel> directores = directorService.listPage(pageable);
        if (directores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(directores, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<DirectorModel> consultarDirector(@PathVariable("id") int id){
		DirectorModel directorm= directorService.consultarDirector(id);
		return new ResponseEntity<DirectorModel>(directorm,HttpStatus.OK);
		
	}
	
	@PostMapping(path="",consumes="application/json")
	public ResponseEntity<String> insertarDirector(@RequestBody DirectorModel directorm){
		directorService.addDirector(directorm);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarDirector(@RequestBody Director director){
		directorService.updateDirector(director);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarDirector(@PathVariable("id")int id){
		directorService.removeDirector(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
}
