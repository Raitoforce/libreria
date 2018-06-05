package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.sql.Date;
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
	
	public abstract List<HistorialVenta> findByNumresurtido(int num);
	
	@Query("select max(hv.fechaSolicitud) from HistorialVenta hv where venta.folio=?1 and hv.numresurtido= ?2")
	public abstract Date getDate(String folio,int num);
	
	@Query("select DISTINCT  hv.numresurtido from HistorialVenta hv where venta.folio=?1")
	public abstract List<Integer> getNumResurtidos(String folio);
	
	//public abstract List<HistorialVenta> findBy;
	
}
