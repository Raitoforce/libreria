package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Profesor;

@Repository("profesorJPARepository")
public interface ProfesorJPARepository extends JpaRepository<Profesor,Serializable>{
	public abstract Profesor findByIdprofesor(int idprofesor);
	
	public abstract List<Profesor> findByEscuelasClave(String clave);
}
