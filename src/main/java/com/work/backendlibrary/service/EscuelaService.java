package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.model.EscuelaModel;

public interface EscuelaService {
	public abstract List<Escuela> listAllEscuelas();
	public abstract Escuela addEscuela(Escuela escuela);
	public abstract void removeEscuela(String clave);
	public abstract Escuela updateEscuela(Escuela escuela);
	public abstract Escuela consultarEscuela(String clave);
	public abstract List<Escuela> consultarDirector(int id);
	public abstract List<EscuelaModel> listpage(Pageable pageable);
	public abstract EscuelaModel listmodel(String escuela);
}
