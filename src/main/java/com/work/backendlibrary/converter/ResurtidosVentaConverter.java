package com.work.backendlibrary.converter;


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
	

	public List<ResurtidosModel> convertir(String folio){
		List<ResurtidosModel> rms=new ArrayList<>();
		for (Integer resurtido: hvJPA.getNumResurtidos(folio)) {
			ResurtidosModel rm=new ResurtidosModel();
			rm.setNumresurtido(resurtido);
			rm.setFecha(hvJPA.getDate(folio, resurtido));
			rms.add(rm);
		}
		return rms;
	}
}
