package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.ProfesorConverter;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;
import com.work.backendlibrary.repository.ProfesorJPARepository;
import com.work.backendlibrary.service.ProfesorService;

@Service("profesorServiceImpl")
public class ProfesorServiceImpl implements ProfesorService{
	
	@Autowired
	@Qualifier("profesorJPARepository")
	private ProfesorJPARepository profesorJPA;
	
	@Autowired
	@Qualifier("profesorConverter")
	private ProfesorConverter profesorc;

	@Override
	public List<Profesor> listAllProfesores() {
		return profesorJPA.findAll();
	}

	@Override
	public Profesor addProfesor(Profesor profesor) {
		return profesorJPA.save(profesor);
	}

	@Override
	public void removeProfesor(int id) {
		profesorJPA.deleteById(id);
	}

	@Override
	public Profesor updateProfesor(Profesor profesor) {
		//Profesor  profesor=profesorc.entity2entity(profesorm);
		return profesorJPA.save(profesor);
	}

	@Override
	public Profesor consultarProfesor(int id) {
		Profesor profesorm = profesorJPA.findByIdprofesor(id);
		return profesorm;
	}

	@Override
	public List<ProfesorModel> listPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return profesorc.listEntity2listmodel(profesorJPA.findAll(pageable).getContent());
	}

	@Override
	public List<Profesor> findByEscuela(String clave) {
		// TODO Auto-generated method stub
		return profesorJPA.findByEscuelasClave(clave);
	}

}
