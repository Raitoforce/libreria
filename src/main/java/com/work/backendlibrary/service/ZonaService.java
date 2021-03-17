package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Zona;

public interface ZonaService {
	List<Zona> listAllZonas();
	Zona addZona(Zona zona);
	void removeZona(String idzona);
	Zona updateZona(Zona zona);
	Zona consultarZona(String idzona);
	boolean ZonaIsOnDB(String zona);
}
