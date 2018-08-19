package com.work.backendlibrary.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Temporada;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.ProfesorJPARepository;
import com.work.backendlibrary.repository.TemporadaJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;

@Component("comisionConverter")
public class ComisionConverter {
	@Autowired
	@Qualifier("temporadaJPARepository")
	TemporadaJPARepository tJPA;
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vJPA;
	
	@Autowired
	@Qualifier("directorJPARepository")
	DirectorJPARepository dJPA;
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;
	
	public Comision entity2modelD(ComisionModel cm){
		Comision c=new Comision();
		Director d=dJPA.findByIddirector(cm.getDirector());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());
		c.setDirector(d);
		c.setTemporada(t);
		c.setFecha(new Date(System.currentTimeMillis()));
		c.setMonto(cm.getMonto());
		c.setTipo("DIRECTOR");
		return c;
	}
	
	public Comision entity2modelV(ComisionModel cm){
		Comision c=new Comision();
		Vendedor v=vJPA.findByClave(cm.getVendedor());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());
		c.setVendedor(v);
		c.setTemporada(t);
		c.setFecha(new Date(System.currentTimeMillis()));
		c.setMonto(cm.getMonto());
		c.setTipo("VENDEDOR");
		return c;
	}
	
	public Comision entity2modelP(ComisionModel cm){
		Comision c=new Comision();
		Profesor profesor = pJPA.findByIdprofesor(cm.getLider());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());
		//c.setLider(new ); Hacer metodo para asignar automatico...
		c.setTemporada(t);
		c.setFecha(new Date(System.currentTimeMillis()));
		c.setMonto(cm.getMonto());
		c.setTipo("LIDER");
		return c;
	}
}
