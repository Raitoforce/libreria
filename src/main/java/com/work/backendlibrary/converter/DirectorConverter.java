package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.model.DirectorModel;

@Component("directorConverter")
public class DirectorConverter {
	
	public DirectorModel entity2model(Director director){
		DirectorModel directorm=new DirectorModel();
		directorm.setNombre(director.getNombre());
		directorm.setApellidos(director.getApellidos());
		directorm.setTelefono(director.getTelefono());
		directorm.setEmail(director.getEmail());
		return directorm;
	}
	
	public DirectorModel model2model(Director director){
		DirectorModel directorm=new DirectorModel();
		directorm.setIddirector(director.getIddirector());
		directorm.setNombre(director.getNombre());
		directorm.setApellidos(director.getApellidos());
		directorm.setTelefono(director.getTelefono());
		directorm.setEmail(director.getEmail());
		return directorm;
	}
	

	public Director model2entity(DirectorModel directorm){
		Director director=new Director();
		director.setNombre(directorm.getNombre());
		director.setApellidos(directorm.getApellidos());
		director.setTelefono(directorm.getTelefono());
		director.setEmail(directorm.getEmail());
		return director;
	}

	public Director entity2entity(DirectorModel directorm){
		Director director=new Director();
		director.setIddirector(directorm.getIddirector());
		director.setNombre(directorm.getNombre());
		director.setApellidos(directorm.getApellidos());
		director.setTelefono(directorm.getTelefono());
		director.setEmail(directorm.getEmail());
		return director;
	}
	public List<DirectorModel> listEntity2listmodel(List<Director> directors){
		List<DirectorModel> list = new ArrayList<DirectorModel>();
		DirectorModel directorm;
		for(Director director: directors){
			directorm=model2model(director);
			list.add(directorm);
		}
		
		return list;
	}

}
