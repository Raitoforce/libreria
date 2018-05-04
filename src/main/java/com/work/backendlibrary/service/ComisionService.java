package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Comision;

public interface ComisionService {
	public abstract List<Comision> listAllComisiones();
	public abstract	Comision addComision(Comision comision);
	public abstract void removeComision(int idComision);
	public abstract Comision updateComision(Comision comision);
	public abstract Comision consultarComision(int idComision);
}
