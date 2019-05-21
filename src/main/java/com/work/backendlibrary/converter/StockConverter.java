package com.work.backendlibrary.converter;

import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.StockModel;
import com.work.backendlibrary.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("stockConverter")
public class StockConverter {
    @Autowired
    @Qualifier("libroServiceImpl")
    LibroService libroService;

    public Stock model2entity(StockModel stockModel) {
        Stock stock = new Stock();
        stock.setLibro(libroService.consultarLibro(stockModel.getLibro()));
        stock.setCantidad(stockModel.getCantidad());
        stock.setFecha_entrada(stockModel.getFecha_entrada());
        stock.setStock_actual(stockModel.getStock_actual());
        stock.setMotivo(stockModel.getMotivo());
        stock.setTipomovimiento(stockModel.getTipomovimiento());
        stock.setHacienda(stockModel.getHacienda());
        stock.setIdstock(stockModel.getIdstock());
        return stock;
    }
}
