package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.DirectorConverter;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.model.DirectorModel;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.service.DirectorService;

@Service("directorServiceImpl")
public class DirectorServiceImpl implements DirectorService{
	
	@Autowired
	@Qualifier("directorConverter")
	DirectorConverter directorc;
	
	@Autowired
	@Qualifier("directorJPARepository")
	DirectorJPARepository directorJPA;

	@Override
	public List<DirectorModel> listAllDirectores() {
		return directorc.listEntity2listmodel(directorJPA.findAll());
	}

	@Override
	public Director addDirector(DirectorModel directorm) {
		Director director= directorc.model2entity(directorm);
		return directorJPA.save(director);
	}

	@Override
	public void removeDirector(int id) {
		directorJPA.deleteById(id);
	}

	@Override
	public Director updateDirector(DirectorModel directorm) {
		Director director= directorc.entity2entity(directorm);
		return directorJPA.save(director);
	}

	@Override
	public DirectorModel consultarDirector(int id) {
		DirectorModel directorm=directorc.model2model(directorJPA.findByIddirector(id));
		return directorm;
	}
	
}
