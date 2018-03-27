package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Folio;

@Repository("folioJPARepository")
public interface FolioJPARepository extends JpaRepository<Folio,Serializable> {
	public abstract List<Folio> findByIdtemporadaIdtemporada(int id);
	public abstract Folio findByIdfolios(int id);
}
