package com.work.backendlibrary.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.CuentasPorCobrar;

@Repository("cuentasPorCobrarJPARepository")
public interface CuentasPorCobrarJPARepository extends JpaRepository<CuentasPorCobrar, Serializable>{
	public abstract CuentasPorCobrar findByIdMovimiento(int id);
}
