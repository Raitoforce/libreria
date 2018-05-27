package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.StockModel;

import java.util.List;

public interface StockService {
    public abstract List<Stock> listAllStock();
    public abstract Stock addStock(StockModel stockModel);
    public abstract void removeStock(int id);
    public abstract Stock updateStock(StockModel stockModel);
    public abstract Stock consultarStock(int id);
    public abstract List<Stock> consultarByLibro(String clave);
    public abstract Stock updtateInventario(Stock stock);
}
