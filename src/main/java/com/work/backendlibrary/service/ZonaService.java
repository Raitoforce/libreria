package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Zona;

public interface ZonaService {
	public abstract List<Zona> listAllZonas();
	public abstract	Zona addZona(Zona zona);
	public abstract void removeZona(String idzona);
	public abstract Zona updateZona(Zona zona);
	public abstract Zona consultarZona(String idzona);
}
