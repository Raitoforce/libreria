package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Folio;

@Repository("folioJPARepository")
public interface FolioJPARepository extends JpaRepository<Folio,Serializable> {
	public abstract List<Folio> findByIdtemporadaIdtemporada(int id);
	public abstract Folio findByIdfolios(int id);
	
	@Transactional
	@Query("select f from Folio f where inicio <= ?1 and fin >= ?1 and tipo=?2 and idtemporada.idtemporada = ?3")
	public abstract Folio findByIsRange(int valor, String tipo,int idtemporada);
}
