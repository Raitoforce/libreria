package com.work.backendlibrary.converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.model.ResurtidosModel;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;

@Component("resurtidosVentaConverter")
public class ResurtidosVentaConverter {
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	

	public ResurtidosModel convertir(String folio){
		ResurtidosModel rm=new ResurtidosModel();
		rm.setFolio(folio);
		rm.setNumresurtidos(hvJPA.getNumResurtidos(folio));
		List<Date> fechas= new ArrayList<>();
		for (Integer resurtido : rm.getNumresurtidos()) {
			fechas.add(hvJPA.getDate(folio, resurtido));
		}
		rm.setFechas(fechas);
		return rm;
	}
}
