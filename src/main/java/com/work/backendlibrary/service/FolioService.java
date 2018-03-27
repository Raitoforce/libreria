package com.work.backendlibrary.service;

import java.util.List;


import com.work.backendlibrary.entity.Folio;

public interface FolioService {
	public abstract List<Folio> listAllFolios();
	public abstract Folio addFolio(Folio folio);
	public abstract void removeFolio(int id);
	public abstract Folio updateFolio(Folio folio);
	public abstract Folio consultarFolio(int id);
	public abstract List<Folio> consultarTemporada(int id);
}
