package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;

public interface ProfesorService {
	public abstract List<Profesor> listAllProfesores();
	public abstract Profesor addProfesor(ProfesorModel profesorm);
	public abstract void removeProfesor(int id);
	public abstract Profesor updateProfesor(Profesor profesor);
	public abstract Profesor consultarProfesor(int id);
	public abstract List<ProfesorModel> listPage(Pageable pageable);
}
