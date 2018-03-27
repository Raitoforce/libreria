package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.repository.EscuelaJPARepository;
import com.work.backendlibrary.service.EscuelaService;

@Service("escuelaServiceImpl")
public class EscuelaServiceImpl implements EscuelaService{
	@Autowired
	@Qualifier("escuelaJPARepository")
	EscuelaJPARepository escuelajpa;

	@Override
	public List<Escuela> listAllEscuelas() {
		// TODO Auto-generated method stub
		return escuelajpa.findAll();
	}

	@Override
	public Escuela addEscuela(Escuela escuela) {
		// TODO Auto-generated method stub
		return escuelajpa.save(escuela);
	}

	@Override
	public void removeEscuela(String clave) {
		// TODO Auto-generated method stub
		escuelajpa.deleteByClave(clave);;
	}

	@Override
	public Escuela updateEscuela(Escuela escuela) {
		// TODO Auto-generated method stub
		return escuelajpa.save(escuela);
	}

	@Override
	public Escuela consultarEscuela(String clave) {
		// TODO Auto-generated method stub
		return escuelajpa.findByClave(clave);
	}

	@Override
	public List<Escuela> consultarDirector(int id) {
		// TODO Auto-generated method stub
		return escuelajpa.findByDirectorIddirector(id);
	}
	
}
