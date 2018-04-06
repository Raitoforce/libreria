package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.BloqueFolio;

@Repository("bloqueFolioJPARepository")
public interface BloqueFoliosJPARepository extends JpaRepository<BloqueFolio, Serializable>{
	public abstract BloqueFolio findByVendedorClaveAndFolioIdfolios(String clave,int id);
}
