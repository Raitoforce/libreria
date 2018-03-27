package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Temporada;

@Repository("temporadaJPARepository")
public interface TemporadaJPARepository extends JpaRepository<Temporada, Serializable>{
	public abstract Temporada findByIdtemporada(int idtemporada);
}
