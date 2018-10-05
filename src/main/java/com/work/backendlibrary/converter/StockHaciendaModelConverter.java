package com.work.backendlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.HaciendaStock;
import com.work.backendlibrary.model.StockHaciendaModel;
import com.work.backendlibrary.service.LibroService;

@Component("haciendaStockConverter")
public class StockHaciendaModelConverter {
	@Autowired
    @Qualifier("libroServiceImpl")
    LibroService libroService;

    public HaciendaStock model2entity(StockHaciendaModel stockModel){
        HaciendaStock stock=new HaciendaStock();
        stock.setLibro(libroService.consultarLibro(stockModel.getLibro()));
        stock.setCantidad(stockModel.getCantidad());
        stock.setFecha_entrada(stockModel.getFecha_entrada());
        stock.setStock_actual(stockModel.getStock_actual());
        stock.setMotivo(stockModel.getMotivo());
        stock.setTipomovimiento(stockModel.getTipomovimiento());
        stock.setId(stockModel.getIdstock());
        return stock;
    }
}
