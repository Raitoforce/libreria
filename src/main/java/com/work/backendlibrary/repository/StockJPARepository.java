package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("stockJPARepository")
public interface StockJPARepository extends JpaRepository<Stock,Serializable> {
    public Stock findByIdstock(int id);
}
