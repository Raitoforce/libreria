package com.work.backendlibrary.service;

import java.sql.Date;
import java.util.List;

import com.work.backendlibrary.entity.Temporada;


public interface TemporadaService {
	List<Temporada> listAllTemporadas();
	Temporada addTemporada(Temporada temporada);
	void removeTemporada(int id);
	Temporada updateTemporada(Temporada temporada);
	Temporada consultarTemporada(int id);
	Temporada actualTemporada();
	boolean rangoTemporada(Date date);
}
