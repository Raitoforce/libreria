package com.work.backendlibrary.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Folio;
import com.work.backendlibrary.repository.FolioJPARepository;
import com.work.backendlibrary.service.FolioService;

@Service("folioServiceImpl")
public class FolioServiceImpl implements FolioService{
	@Autowired
	@Qualifier("folioJPARepository")
	FolioJPARepository fjpa;
	
	@Override
	public List<Folio> listAllFolios(){
		//Hibernate.initialize(fjpa);
		return fjpa.findAll();
	}

	@Override
	public Folio addFolio(Folio folio) {
		// TODO Auto-generated method stub
		return fjpa.save(folio);
	}

	@Override
	public void removeFolio(int id) {
		fjpa.deleteById(id);
	}

	@Override
	public Folio updateFolio(Folio folio) {
		// TODO Auto-generated method stub
		return fjpa.save(folio);
	}

	@Override
	public Folio consultarFolio(int id) {
		// TODO Auto-generated method stub
		return fjpa.findByIdfolios(id);
	}

	@Override
	public List<Folio> consultarTemporada(int id) {
		// TODO Auto-generated method stub
		//Hibernate.initialize(fjpa);
		return fjpa.findByIdtemporadaIdtemporada(id);
	}

	@Override
	public boolean isInRange(int valor, String tipo) {
		if(fjpa.findByIsRange(valor,tipo)!=null)
			return true;
		return false;
	}

}
