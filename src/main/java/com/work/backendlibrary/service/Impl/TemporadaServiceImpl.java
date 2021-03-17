package com.work.backendlibrary.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Temporada;
import com.work.backendlibrary.repository.TemporadaJPARepository;
import com.work.backendlibrary.service.TemporadaService;

@Service("temporadaServiceImpl")
public class TemporadaServiceImpl implements TemporadaService{
	
	@Autowired
	@Qualifier("temporadaJPARepository")
	TemporadaJPARepository temporadaJPA;

	@Override
	public List<Temporada> listAllTemporadas() {
		return temporadaJPA.findAll();
	}

	@Override
	public Temporada addTemporada(Temporada temporada) {
		return temporadaJPA.save(temporada);
	}

	@Override
	public void removeTemporada(int id) {
		temporadaJPA.deleteById(id);
	}

	@Override
	public Temporada updateTemporada(Temporada temporada) {
		return temporadaJPA.save(temporada);
	}

	@Override
	public Temporada consultarTemporada(int id) {
		return temporadaJPA.findByIdtemporada(id);
	}

	@Override
	public boolean rangoTemporada(Date date) {
		// TODO Auto-generated method stub
		Temporada temporada=temporadaJPA.findByFechaActualBeetwenFechaInicioAndFechaFin(date);
        return temporada != null;
	}
	@Override
	public Temporada actualTemporada() {
		// TODO Auto-generated method stub
		Date date=new Date(System.currentTimeMillis());
		Temporada temporada=temporadaJPA.findByFechaActualBeetwenFechaInicioAndFechaFin(date);
		return temporada;
	}

}
