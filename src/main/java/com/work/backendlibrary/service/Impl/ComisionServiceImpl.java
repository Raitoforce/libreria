package com.work.backendlibrary.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.ComisionConverter;
import com.work.backendlibrary.converter.ComisionesModuloConverter;
import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;
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
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vJPA;
	
	@Autowired
	@Qualifier("directorJPARepository")
	DirectorJPARepository dJPA;

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
		return cmc.cuentaDirector(iddirector, idtemporada);
	}

	@Override
	public List<ComisionesVistaModel> consultarComisionesByVendedors(int idtemporada) {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores=vJPA.findAll();
		List<ComisionesVistaModel> cvms= new ArrayList<>();
		ComisionesVistaModel cvm=null;
		for (Vendedor vendedor : vendedores) {
			cvm=consultarComisionesByVendedor(vendedor.getClave(), idtemporada);
			if(cvm.getDeuda()!=0)
				cvms.add(cvm);
		}
		return cvms;
	}

	@Override
	public List<ComisionesVistaModel> consultarComisionesByDirectors(int idtemporada) {
		/// 
		List<Director> directores=dJPA.findAll();
		List<ComisionesVistaModel> cvms= new ArrayList<>();
		ComisionesVistaModel cvm=null;
		for (Director director : directores) {
			cvm=consultarComisionesByDirector(director.getIddirector(),idtemporada);
			if(cvm.getDeuda()!=0)
				cvms.add(cvm);
		}
		return cvms;
	}

}
