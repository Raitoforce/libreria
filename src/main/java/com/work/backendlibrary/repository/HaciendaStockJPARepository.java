package com.work.backendlibrary.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.backendlibrary.entity.HaciendaStock;

@Repository("haciendaStockJPARepository")
public interface HaciendaStockJPARepository extends JpaRepository<HaciendaStock,Serializable>{
	public HaciendaStock findById(int id);
    
    public List<HaciendaStock> findByLibroClaveProducto(String clave);
}
