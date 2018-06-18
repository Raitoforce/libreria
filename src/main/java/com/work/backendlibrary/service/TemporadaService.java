package com.work.backendlibrary.service;

import java.sql.Date;
import java.util.List;

import com.work.backendlibrary.entity.Temporada;


public interface TemporadaService {
	public abstract List<Temporada> listAllTemporadas();
	public abstract Temporada addTemporada(Temporada temporada);
	public abstract void removeTemporada(int id);
	public abstract Temporada updateTemporada(Temporada temporada);
	public abstract Temporada consultarTemporada(int id);
	public abstract Temporada actualTemporada();
	public abstract boolean rangoTemporada(Date date);
}
