package com.work.backendlibrary.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.Venta;

@Repository("ventaJPARepository")
public interface VentaJPARepository extends JpaRepository<Venta, Serializable>{
	public abstract Venta findByFolio(String folio);
	
	public abstract Venta findByFolioAndBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporada(String folio,String clave,int idtemporada);
}
