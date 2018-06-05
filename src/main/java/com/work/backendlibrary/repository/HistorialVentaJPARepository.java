package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.HistorialVenta;

@Repository("historialVentaJPARepository")
public interface HistorialVentaJPARepository extends JpaRepository<HistorialVenta, Serializable>{
	
	public abstract List<HistorialVenta> findByVentaFolio(String id);
	
	public abstract HistorialVenta findByIdHistorial(int id);
	
	@Query("select max(hv.numresurtido) from HistorialVenta hv where venta.folio=?1")
	public abstract int getMAX(String folio);
	
	
	
	//public abstract List<HistorialVenta> findBy;
	
}
