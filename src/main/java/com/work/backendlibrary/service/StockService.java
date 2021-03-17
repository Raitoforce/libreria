package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.StockModel;

import java.util.List;

public interface StockService {
    List<Stock> listAllStock();
    Stock addStock(StockModel stockModel);
    void removeStock(int id);
    Stock updateStock(StockModel stockModel);
    Stock consultarStock(int id);
    List<Stock> consultarByLibro(String clave);
    Stock updtateInventario(Stock stock);

    List<Stock> getByHacienda(int hacienda);
    List<Stock> consultarByLibroHacienda(String clave, int hacienda);
}
