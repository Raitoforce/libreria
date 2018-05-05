package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Temporada;

@Repository("temporadaJPARepository")
public interface TemporadaJPARepository extends JpaRepository<Temporada, Serializable>{
	public abstract Temporada findByIdtemporada(int idtemporada);
	
	@Transactional
	@Query("select t from Temporada t where fecha_inicio <= ?1 and fecha_termino >= ?1")
	public abstract Temporada findByFechaActualBeetwenFechaInicioAndFechaFin(Date date);
}
