package com.work.backendlibrary.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.StockHaciendaModelConverter;
import com.work.backendlibrary.entity.HaciendaStock;
import com.work.backendlibrary.model.StockHaciendaModel;
import com.work.backendlibrary.repository.HaciendaStockJPARepository;
import com.work.backendlibrary.repository.LibroJPARepository;
import com.work.backendlibrary.service.HaciendaStockService;

@Service("haciendaStockService")
public class HaciendaStockServiceImpl implements HaciendaStockService{
	
	@Autowired
    @Qualifier("haciendaStockJPARepository")
    HaciendaStockJPARepository stockJPARepository;

    @Autowired
    @Qualifier("haciendaStockConverter")
    StockHaciendaModelConverter stockConverter;
    
    @Autowired
    @Qualifier("libroJPARepository")
    LibroJPARepository libroJPA;

	@Override
	public List<HaciendaStock> listAllStock() {
		 return stockJPARepository.findAll();
	}

	@Override
	public HaciendaStock addStock(StockHaciendaModel stockModel) {
		HaciendaStock stock=stockConverter.model2entity(stockModel);
        stock.setStock_actual(stock.getCantidad());
        stock.setFecha_entrada(new Date(System.currentTimeMillis()));
        return stockJPARepository.save(stock);
	}

	@Override
	public void removeStock(int id) {
		stockJPARepository.deleteById(id);
	}

	@Override
	public HaciendaStock updateStock(StockHaciendaModel stockModel) {
		HaciendaStock stock=stockConverter.model2entity(stockModel);
        return stockJPARepository.save(stock);
	}

	@Override
	public HaciendaStock consultarStock(int id) {
		return stockJPARepository.findById(id);
	}

	@Override
	public List<HaciendaStock> consultarByLibro(String clave) {
		// TODO Auto-generated method stub
		return stockJPARepository.findByLibroClaveProducto(clave);
	}

}
