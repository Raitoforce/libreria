package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.LideresComisiones;


@Repository("lideresComisionesJPARepository")
public interface LideresComisionesJPARepository extends JpaRepository<LideresComisiones,Serializable>{
	public abstract LideresComisiones findByIdProfesorAndIdVenta(int idprofesor,String folio);	
}
