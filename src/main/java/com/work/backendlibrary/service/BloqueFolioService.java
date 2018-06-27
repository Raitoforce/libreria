package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.BloqueFolio;

public interface BloqueFolioService {
	public abstract List<BloqueFolio> listAllBloqueFolios();
	public abstract BloqueFolio addBloqueFolio(BloqueFolio bf);
	public abstract void removeBloqueFolio(String clave,int id);
	public abstract BloqueFolio updateFolio(BloqueFolio bf);
	public abstract BloqueFolio consultarFolio(String clave, int id);
	public abstract boolean isInRange(int valor,int idfolio);
	public abstract BloqueFolio consultarByVendedorAndTemporada(String clave,int idtemporada);
	
	public abstract BloqueFolio isInRangeAndVendedor(String clave,int valor,int idtemporada);
	public abstract boolean isAValidFolio(String clave,int valor, int idtemporada);
	public abstract boolean isAValidFolioType(String clave,int valor, String type, int idtemporada);
}
