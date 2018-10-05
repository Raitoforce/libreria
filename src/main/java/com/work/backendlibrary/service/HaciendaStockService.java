package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.HaciendaStock;
import com.work.backendlibrary.model.StockHaciendaModel;;

public interface HaciendaStockService {
	public abstract List<HaciendaStock> listAllStock();
    public abstract HaciendaStock addStock(StockHaciendaModel stockModel);
    public abstract void removeStock(int id);
    public abstract HaciendaStock updateStock(StockHaciendaModel stockModel);
    public abstract HaciendaStock consultarStock(int id);
    public abstract List<HaciendaStock> consultarByLibro(String clave);
}
