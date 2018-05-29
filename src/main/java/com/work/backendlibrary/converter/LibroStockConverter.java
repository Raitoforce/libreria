package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.LibroStockModel;
import com.work.backendlibrary.service.LibroService;

@Component("libroStockConverter")
public class LibroStockConverter {
	@Autowired
	@Qualifier("libroServiceImpl")
	LibroService libroService;
	
	public List<LibroStockModel> convertir(){
		List<LibroStockModel> librosM= new ArrayList<>();
		for (Libro libro: libroService.listAllLibros()) {
			LibroStockModel lsm=new LibroStockModel();
			lsm.setClaveProducto(libro.getClave_producto());
			lsm.setTitulo(libro.getTitulo());
			int cont=0;
			for (Stock stock : libro.getStocks()) {
				cont+=stock.getStock_actual();
			}
			lsm.setCantidad(cont);
			librosM.add(lsm);
		}
		return librosM;
	}
	
}