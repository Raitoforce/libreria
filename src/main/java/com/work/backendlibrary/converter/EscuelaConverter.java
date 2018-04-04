package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.model.EscuelaModel;

@Component("escuelaConverter")
public class EscuelaConverter {
	@Autowired
	@Qualifier("directorConverter")
	DirectorConverter dc;
	
	public EscuelaModel entity2model(Escuela e){
		EscuelaModel em=new EscuelaModel();
		em.setClave(e.getClave());
		em.setCodigoPostal(e.getCodigoPostal());
		em.setColonia(e.getColonia());
		em.setDireccion(e.getDireccion());
		em.setNombre(e.getNombre());
		em.setEmail(e.getEmail());
		em.setEstado(e.getEstado());
		em.setMunicipio(e.getMunicipio());
		em.setTelefono(e.getTelefono());
		em.setTurno(e.getTurno());
		em.setDirector(dc.entity2model(e.getDirector()));
		
		return em;
	}
	
	public Escuela model2entity(EscuelaModel em){
		Escuela e=new Escuela();
		e.setClave(em.getClave());
		e.setCodigoPostal(em.getCodigoPostal());
		e.setColonia(em.getColonia());
		e.setDireccion(em.getDireccion());
		e.setNombre(em.getNombre());
		e.setEmail(em.getEmail());
		e.setEstado(em.getEstado());
		e.setMunicipio(em.getMunicipio());
		e.setTelefono(em.getTelefono());
		e.setTurno(em.getTurno());
		e.setDirector(dc.model2entity(em.getDirector()));
		
		return e;
	}
	
	public List<EscuelaModel> listEntity2listmodel(List<Escuela> escuelas){
		List<EscuelaModel> list = new ArrayList<EscuelaModel>();
		EscuelaModel em;
		for(Escuela e: escuelas){
			em=entity2model(e);

			list.add(em);
		}
		
		return list;
	}
	
	
}
