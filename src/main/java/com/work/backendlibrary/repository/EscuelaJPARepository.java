package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Escuela;

@Repository("escuelaJPARepository")
public interface EscuelaJPARepository extends JpaRepository<Escuela,Serializable>,CrudRepository<Escuela,Serializable>{
	List<Escuela> findByDirectorIddirector(int id);
	
	@Transactional
	@Query("SELECT escuela from Escuela escuela where clave=?1")
    Escuela findByClave(String clave);
	
	@Modifying
	@Transactional
	@Query("delete from Escuela escuela where clave=?1")
    void deleteByClave(String clave);
	
	List<Escuela> findByZonaIdzona(String idzona);
	
}
