package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.ComisionConverter;
import com.work.backendlibrary.converter.ComisionesModuloConverter;
import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.service.ComisionService;

@Service("comisionService")
public class ComisionServiceImpl implements ComisionService{
	@Autowired
	@Qualifier("comisionJPARepository")
	ComisionJPARepository comisionJPA;
	

	@Autowired
	@Qualifier("comisionesModuloConverter")
	ComisionesModuloConverter cmc;
	
	@Autowired
	@Qualifier("comisionConverter")
	ComisionConverter cc;

	@Override
	public List<Comision> listAllComisiones() {
		// TODO Auto-generated method stub
		return comisionJPA.findAll();
	}

	@Override
	public Comision addComision(Comision comision) {
		// TODO Auto-generated method stub
		return comisionJPA.save(comision);
	}

	@Override
	public void removeComision(int idComision) {
		// TODO Auto-generated method stub
		comisionJPA.deleteById(idComision);
	}

	@Override
	public Comision updateComision(Comision comision) {
		// TODO Auto-generated method stub
		return comisionJPA.save(comision);
	}

	@Override
	public Comision consultarComision(int idComision) {
		// TODO Auto-generated method stub
		return comisionJPA.findByIdComision(idComision);
	}

	@Override
	public Comision addComisionVendedor(ComisionModel cm) {
		// TODO Auto-generated method stub
		return comisionJPA.save(cc.entity2modelV(cm));
	}

	@Override
	public Comision addComisionDirector(ComisionModel cm) {
		// TODO Auto-generated method stub
		return comisionJPA.save(cc.entity2modelD(cm));
	}

	@Override
	public ComisionesVistaModel consultarComisionesByVendedor(String clave, int idtemporada) {
		// TODO Auto-generated method stub
		return cmc.cuentaVendedor(clave, idtemporada);
	}

	@Override
	public ComisionesVistaModel consultarComisionesByDirector(int iddirector, int idtemporada) {
		// TODO Auto-generated method stub
		return cmc.cuentaDirector(iddirector, idtemporada);
	}

}
