package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("stockJPARepository")
public interface StockJPARepository extends JpaRepository<Stock,Serializable> {
    Stock findByIdstock(int id);
    
    List<Stock> findByLibroClaveProducto(String clave);

    List<Stock> findByHacienda(int hacienda);
    
    List<Stock> findByLibroClaveProductoAndHacienda(String clave, int hacienda);

}
