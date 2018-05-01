package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.BloqueFolio;

public interface BloqueFolioService {
	public abstract List<BloqueFolio> listAllBloqueFolios();
	public abstract BloqueFolio addBloqueFolio(BloqueFolio bf);
	public abstract void removeBloqueFolio(String clave,int id);
	public abstract BloqueFolio updateFolio(BloqueFolio bf);
	public abstract BloqueFolio consultarFolio(String clave, int id); 
}
