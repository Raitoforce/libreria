package com.work.backendlibrary.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Comision;

@Repository("comisionJPARepository")
public interface ComisionJPARepository extends JpaRepository<Comision, Serializable>{
	public abstract Comision findByIdComision(int id);
}
