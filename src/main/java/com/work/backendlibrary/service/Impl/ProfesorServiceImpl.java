package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public List<ProfesorModel> listAllProfesores() {
		return profesorc.listEntity2listmodel(profesorJPA.findAll());
	}

	@Override
	public Profesor addProfesor(ProfesorModel profesorm) {
		Profesor  profesor=profesorc.model2entity(profesorm);
		return profesorJPA.save(profesor);
	}

	@Override
	public void removeProfesor(int id) {
		profesorJPA.deleteById(id);
	}

	@Override
	public Profesor updateProfesor(ProfesorModel profesorm) {
		Profesor  profesor=profesorc.entity2entity(profesorm);
		return profesorJPA.save(profesor);
	}

	@Override
	public ProfesorModel consultarProfesor(int id) {
		ProfesorModel profesorm=profesorc.model2model(profesorJPA.findByIdprofesor(id));
		return profesorm;
	}

}
