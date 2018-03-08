package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;

public interface ProfesorService {
	public abstract List<ProfesorModel> listAllProfesores();
	public abstract Profesor addProfesor(ProfesorModel profesorm);
	public abstract void removeProfesor(int id);
	public abstract Profesor updateProfesor(ProfesorModel profesorm);
	public abstract ProfesorModel consultarProfesor(int id);
}
