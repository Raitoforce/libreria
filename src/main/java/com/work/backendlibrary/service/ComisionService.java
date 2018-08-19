package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;

public interface ComisionService {
	public abstract List<Comision> listAllComisiones();
	public abstract	Comision addComision(Comision comision);
	public abstract void removeComision(int idComision);
	public abstract Comision updateComision(Comision comision);
	public abstract Comision consultarComision(int idComision);
	
	
	public abstract Comision addComisionVendedor(ComisionModel cm);
	public abstract Comision addComisionDirector(ComisionModel cm);
	public abstract Comision addComisionLider(ComisionModel cm);
	
	public abstract ComisionesVistaModel consultarComisionesByVendedor(String clave,int idtemporada);
	public abstract ComisionesVistaModel consultarComisionesByDirector(int iddirector,int idtemporada);
	public abstract ComisionesVistaModel consultarComisionesByLider(int idprofesor,int idtemporada);

	public abstract List<ComisionesVistaModel> consultarComisionesByVendedors(int idtemporada);
	public abstract List<ComisionesVistaModel> consultarComisionesByDirectors(int idtemporada);
	public abstract List<ComisionesVistaModel> consultarComisionesByLideres(int idtemporada);
}
