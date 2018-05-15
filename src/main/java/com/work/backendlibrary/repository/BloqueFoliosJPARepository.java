package com.work.backendlibrary.repository;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.BloqueFolio;
import com.work.backendlibrary.entity.Folio;

@Repository("bloqueFolioJPARepository")
public interface BloqueFoliosJPARepository extends JpaRepository<BloqueFolio, Serializable>{
	public abstract BloqueFolio findByVendedorClaveAndFolioIdfolios(String clave,int id);
	
	@Modifying
	@Transactional
	@Query("delete from BloqueFolio bfolio where vendedor_clave = ?1 and folio_idfolios = ?2")
	public void deleteByClaveAndId(String clave, int id);
	
	@Query("select bf from BloqueFolio bf where inicio <= ?1 and fin >= ?1 and folio.idfolios=?2")
	public abstract BloqueFolio findByIsRange(int valor,int folio);
}
