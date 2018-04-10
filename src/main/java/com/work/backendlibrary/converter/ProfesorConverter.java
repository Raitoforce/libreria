package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.model.ProfesorModel;

@Component("profesorConverter")
public class ProfesorConverter {

	public ProfesorModel entity2model(Profesor profesor){
		ProfesorModel profesorm=new ProfesorModel();
		profesorm.setNombre(profesor.getNombre());
		profesorm.setApellidos(profesor.getApellidos());
		profesorm.setTelefono(profesor.getTelefono());
		return profesorm;
	}
	
	public ProfesorModel model2model(Profesor profesor){
		ProfesorModel profesorm=new ProfesorModel();
		//profesorm.setIdprofesor(profesor.getIdprofesor());
		profesorm.setNombre(profesor.getNombre());
		profesorm.setApellidos(profesor.getApellidos());
		profesorm.setTelefono(profesor.getTelefono());
		return profesorm;
	}
	
	
	public Profesor model2entity(ProfesorModel profesorm){
		Profesor profesor=new Profesor();
		profesor.setNombre(profesorm.getNombre());
		profesor.setApellidos(profesorm.getApellidos());
		profesor.setTelefono(profesorm.getTelefono());
		profesor.setEscuelas(profesor.getEscuelas());
		return profesor;
	}
	
	public Profesor entity2entity(ProfesorModel profesorm){
		Profesor profesor=new Profesor();
		//profesor.setIdprofesor(profesorm.getIdprofesor());
		profesor.setNombre(profesorm.getNombre());
		profesor.setApellidos(profesorm.getApellidos());
		profesor.setTelefono(profesorm.getTelefono());
		return profesor;
	}
	
	public List<ProfesorModel> listEntity2listmodel(List<Profesor> profesors){
		List<ProfesorModel> list = new ArrayList<ProfesorModel>();
		ProfesorModel profesorm;
		for(Profesor profesor: profesors){
			profesorm=model2model(profesor);

			list.add(profesorm);
		}
		
		return list;
	}
}
