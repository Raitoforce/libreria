package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Libro;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.InventarioReporteModel;
import com.work.backendlibrary.service.LibroService;

@Component
public class InventarioConverterRConverter {
	@Autowired
	@Qualifier("libroServiceImpl")
	LibroService libroService;
	
	public List<InventarioReporteModel> converterInventarioRModel(){
		List<InventarioReporteModel> irm= new ArrayList<>();
		InventarioReporteModel ir=null;
		for (Libro libro: libroService.listAllLibros()){
			ir.setClaveProducto(libro.getClave_producto());
			ir.setTitulo(libro.getTitulo());
			int cont=0;
			for (Stock stock : libro.getStocks()) {
				cont+=stock.getStock_actual();
			}
			ir.setCantidad(cont);
			ir.setStocks(libro.getStocks());
			irm.add(ir);
		}
		return irm;
	}
}
