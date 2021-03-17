package com.work.backendlibrary.service;

import java.util.List;


import com.work.backendlibrary.entity.Folio;

public interface FolioService {
	List<Folio> listAllFolios();
	Folio addFolio(Folio folio);
	void removeFolio(int id);
	Folio updateFolio(Folio folio);
	Folio consultarFolio(int id);
	List<Folio> consultarTemporada(int id);
	boolean isInRange(int valor, String tipo, int idtemporada);
}
