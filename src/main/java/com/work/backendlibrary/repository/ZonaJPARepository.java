package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Zona;

@Repository("zonaJPARepository")
public interface ZonaJPARepository extends JpaRepository<Zona, Serializable>{
	public abstract List<Zona> findByVendedorClave(String clave);
	
	public abstract Zona findByIdzona(String idzona);
	
}
