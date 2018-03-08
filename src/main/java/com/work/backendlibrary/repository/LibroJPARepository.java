package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.work.backendlibrary.entity.Libro;

@Repository("libroJPARepository")
public interface LibroJPARepository extends JpaRepository<Libro, Serializable>,CrudRepository<Libro,Serializable>{
	public abstract Libro findByIsbn(String isbn);
	
	/*@Modifying
	@Transactional
	@Query("delete from Libro libro where isbn = ?1")
	void deleteByIsbn(String isbn);*/
	
	
}
