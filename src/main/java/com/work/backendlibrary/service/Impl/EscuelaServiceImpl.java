package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.EscuelaConverter;
import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.model.EscuelaModel;
import com.work.backendlibrary.repository.EscuelaJPARepository;
import com.work.backendlibrary.service.EscuelaService;

@Service("escuelaServiceImpl")
public class EscuelaServiceImpl implements EscuelaService{
	@Autowired
	@Qualifier("escuelaJPARepository")
	EscuelaJPARepository escuelajpa;
	
	@Autowired
	@Qualifier("escuelaConverter")
	EscuelaConverter escuelac;

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
		escuelajpa.deleteByClave(clave);
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

	@Override
	public List<EscuelaModel> listpage(Pageable pageable) {
		// TODO Auto-generated method stub
		return escuelac.listEntity2listmodel(escuelajpa.findAll(pageable).getContent());
	}

	@Override
	public EscuelaModel listmodel(String escuela) {
		return null;
	}

	@Override
	public List<Escuela> findByZona(String zona) {
		// TODO Auto-generated method stub
		return escuelajpa.findByZonaIdzona(zona);
	}

	@Override
	public boolean EscuelaIsOnDB(String clave) {
		// TODO Auto-generated method stub
        return escuelajpa.findByClave(clave) != null;
    }

}
