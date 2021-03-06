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
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.model.ComisionesVistaModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.ProfesorJPARepository;
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
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;

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
	public ComisionesVistaModel consultarComisionesByVendedor(String clave, int idtemporada, int hacienda) {
		// TODO Auto-generated method stub
		return cmc.cuentaVendedor(clave, idtemporada, hacienda);
	}

	@Override
	public ComisionesVistaModel consultarComisionesByDirector(int iddirector, int idtemporada , int hacienda) {
		return cmc.cuentaDirector(iddirector, idtemporada, hacienda);
	}

	@Override
	public List<ComisionesVistaModel> consultarComisionesByVendedors(int idtemporada , int hacienda) {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores=vJPA.findAll();
		List<ComisionesVistaModel> cvms= new ArrayList<>();
		ComisionesVistaModel cvm=null;
		for (Vendedor vendedor : vendedores) {
			cvm=consultarComisionesByVendedor(vendedor.getClave(), idtemporada, hacienda);
			if(cvm.getDeuda()!=0)
				cvms.add(cvm);
		}
		return cvms;
	}

	@Override
	public List<ComisionesVistaModel> consultarComisionesByDirectors(int idtemporada , int hacienda) {
		/// 
		List<Director> directores=dJPA.findAll();
		List<ComisionesVistaModel> cvms= new ArrayList<>();
		ComisionesVistaModel cvm=null;
		for (Director director : directores) {
			cvm=consultarComisionesByDirector(director.getIddirector(),idtemporada , hacienda);
			if(cvm.getDeuda()!=0)
				cvms.add(cvm);
		}
		return cvms;
	}

	@Override
	public void addComisionLider(ComisionModel cm, int hacienda) {
		// TODO Auto-generated method stub
		cc.entity2modelP(cm , hacienda);
	}

	@Override
	public ComisionesVistaModel consultarComisionesByLider(int idprofesor, int idtemporada , int hacienda) {
		// TODO Auto-generated method stub
		return cmc.cuentaLider(idprofesor, idtemporada , hacienda);
	}

	@Override
	public List<ComisionesVistaModel> consultarComisionesByLideres(int idtemporada, int hacienda) {
		// TODO Auto-generated method stub
		List<Profesor> profesores=pJPA.findAll();
		List<ComisionesVistaModel> cvms= new ArrayList<>();
		ComisionesVistaModel cvm=null;
		for (Profesor profesors : profesores) {
			cvm=consultarComisionesByLider(profesors.getIdprofesor(), idtemporada , hacienda);
			if(cvm.getDeuda()!=0)
				cvms.add(cvm);
		}
		return cvms;
	}

	@Override
	public List<Comision> consultarHistorialComisionesByVendedors(int idtemporada, String clave) {
		// TODO Auto-generated method stub
		return comisionJPA.findByTemporadaIdtemporadaAndVendedorClaveAndTipo(idtemporada,clave,"VENDEDOR");
	}

	@Override
	public List<Comision> consultarHistorialComisionesByDirectors(int idtemporada, int iddirector) {
		// TODO Auto-generated method stub
		return comisionJPA.findByTemporadaIdtemporadaAndDirectorIddirectorAndTipo(idtemporada,iddirector,"DIRECTOR");
	}

	@Override
	public List<Comision> consultarHistorialComisionesByLideres(int idtemporada, int idprofesor) {
		// TODO Auto-generated method stub
		return comisionJPA.findByTemporadaIdtemporadaAndLiderLiderIdprofesorAndTipo(idtemporada, idprofesor,"LIDER");
	}

}
