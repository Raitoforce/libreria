package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Comision;

@Repository("comisionJPARepository")
public interface ComisionJPARepository extends JpaRepository<Comision, Serializable>{
	public abstract Comision findByIdComision(int id);
	
	public abstract List<Comision> findByTemporadaIdtemporadaAndDirectorIddirectorAndTipo(int idtemporada,int iddirector,String tipo);
	public abstract List<Comision> findByTemporadaIdtemporadaAndVendedorClaveAndTipo(int idtemporada,String claveV,String tipo);

}
