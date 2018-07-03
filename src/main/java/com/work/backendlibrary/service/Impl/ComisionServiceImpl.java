package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.service.ComisionService;

@Service("comisionService")
public class ComisionServiceImpl implements ComisionService{
	@Autowired
	@Qualifier("comisionJPARepository")
	ComisionJPARepository comisionJPA;

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
		return null;
	}

	@Override
	public Comision addComisionDirector(ComisionModel cm) {
		// TODO Auto-generated method stub
		return null;
	}

}
