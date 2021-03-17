package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.model.EscuelaModel;

public interface EscuelaService {
	List<Escuela> listAllEscuelas();
	Escuela addEscuela(Escuela escuela);
	void removeEscuela(String clave);
	Escuela updateEscuela(Escuela escuela);
	Escuela consultarEscuela(String clave);
	List<Escuela> consultarDirector(int id);
	List<EscuelaModel> listpage(Pageable pageable);
	EscuelaModel listmodel(String escuela);
	List<Escuela> findByZona(String zona);
	boolean EscuelaIsOnDB(String clave);
}
