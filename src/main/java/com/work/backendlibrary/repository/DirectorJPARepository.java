package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Director;

@Repository("directorJPARepository")
public interface DirectorJPARepository extends JpaRepository<Director,Serializable>{
	public abstract Director findByIddirector(int iddirector);
}
