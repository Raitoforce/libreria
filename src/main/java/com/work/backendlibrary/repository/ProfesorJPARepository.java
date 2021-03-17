package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Profesor;

@Repository("profesorJPARepository")
public interface ProfesorJPARepository extends JpaRepository<Profesor,Serializable>{
	Profesor findByIdprofesor(int idprofesor);
	
	List<Profesor> findByEscuelasClave(String clave);
}
