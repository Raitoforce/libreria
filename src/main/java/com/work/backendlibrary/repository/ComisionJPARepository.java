package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Comision;

@Repository("comisionJPARepository")
public interface ComisionJPARepository extends JpaRepository<Comision, Serializable>{
	Comision findByIdComision(int id);
	
	List<Comision> findByTemporadaIdtemporadaAndDirectorIddirectorAndTipo(int idtemporada, int iddirector, String tipo);
	List<Comision> findByTemporadaIdtemporadaAndVendedorClaveAndTipo(int idtemporada, String claveV, String tipo);
	List<Comision> findByTemporadaIdtemporadaAndLiderLiderIdprofesorAndTipo(int idtemporada, int idlider, String tipo);
	List<Comision> findByVendedorClaveAndTemporadaIdtemporada(String clave, int idtemporada);

}
