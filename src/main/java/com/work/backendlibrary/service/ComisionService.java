package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;

public interface ComisionService {
	List<Comision> listAllComisiones();
	Comision addComision(Comision comision);
	void removeComision(int idComision);
	Comision updateComision(Comision comision);
	Comision consultarComision(int idComision);
	
	
	Comision addComisionVendedor(ComisionModel cm);
	Comision addComisionDirector(ComisionModel cm);
	void addComisionLider(ComisionModel cm, int hacienda);
	
	ComisionesVistaModel consultarComisionesByVendedor(String clave, int idtemporada, int hacienda);
	ComisionesVistaModel consultarComisionesByDirector(int iddirector, int idtemporada, int hacienda);
	ComisionesVistaModel consultarComisionesByLider(int idprofesor, int idtemporada, int hacienda);

	List<ComisionesVistaModel> consultarComisionesByVendedors(int idtemporada, int hacienda);
	List<ComisionesVistaModel> consultarComisionesByDirectors(int idtemporada, int hacienda);
	List<ComisionesVistaModel> consultarComisionesByLideres(int idtemporada, int hacienda);
	
	List<Comision> consultarHistorialComisionesByVendedors(int idtemporada, String clave);
	List<Comision> consultarHistorialComisionesByDirectors(int idtemporada, int iddirector);
	List<Comision> consultarHistorialComisionesByLideres(int idtemporada, int idprofesor);

}
