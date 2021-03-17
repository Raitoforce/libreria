package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;

public interface ProfesorService {

	Profesor addProfesor(Profesor profesor);
	List<Profesor> listAllProfesores();
	void removeProfesor(int id);
	Profesor updateProfesor(Profesor profesor);
	Profesor consultarProfesor(int id);
	List<ProfesorModel> listPage(Pageable pageable);
	List<Profesor> findByEscuela(String clave);
}
