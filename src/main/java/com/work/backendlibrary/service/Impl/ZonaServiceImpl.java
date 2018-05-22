package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Zona;
import com.work.backendlibrary.repository.ZonaJPARepository;
import com.work.backendlibrary.service.ZonaService;

@Service("zonaService")
public class ZonaServiceImpl implements ZonaService{
	@Autowired
	@Qualifier("zonaJPARepository")
	ZonaJPARepository zonaJPA;
	
	@Override
	public List<Zona> listAllZonas() {
		// TODO Auto-generated method stub
		return zonaJPA.findAll();
	}

	@Override
	public Zona addZona(Zona zona) {
		// TODO Auto-generated method stub
		return zonaJPA.save(zona);
	}

	@Override
	public void removeZona(String idzona) {
		// TODO Auto-generated method stub
		zonaJPA.deleteById(idzona);
	}

	@Override
	public Zona updateZona(Zona zona) {
		// TODO Auto-generated method stub
		return zonaJPA.save(zona);
	}

	@Override
	public Zona consultarZona(String idzona) {
		// TODO Auto-generated method stub
		return zonaJPA.findByIdzona(idzona);
	}

	@Override
	public boolean ZonaIsOnDB(String zona) {
		if(zonaJPA.findByIdzona(zona)!=null)
			return true;
		return false;
	}

}
