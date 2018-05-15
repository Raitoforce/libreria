package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.converter.StockConverter;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.StockModel;
import com.work.backendlibrary.repository.StockJPARepository;
import com.work.backendlibrary.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    @Qualifier("stockJPARepository")
    StockJPARepository stockJPARepository;

    @Autowired
    @Qualifier("stockConverter")
    StockConverter stockConverter;

    @Override
    public List<Stock> listAllStock() {
        return stockJPARepository.findAll();
    }

    @Override
    public Stock addStock(StockModel stockModel){
        Stock stock=stockConverter.model2entity(stockModel);
        return stockJPARepository.save(stock);
    }

    @Override
    public void removeStock(int id) {
        stockJPARepository.deleteById(id);
    }

    @Override
    public Stock updateStock(StockModel stockModel) {
        Stock stock=stockConverter.model2entity(stockModel);
        return stockJPARepository.save(stock);
    }

    @Override
    public Stock consultarStock(int id) {
        return stockJPARepository.findByIdstock(id);
    }

	@Override
	public List<Stock> consultarByLibro(String clave) {
		// TODO Auto-generated method stub
		return stockJPARepository.findByLibroClaveProducto(clave);
	}
}
