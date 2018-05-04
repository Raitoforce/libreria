package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.service.HistorialVentaService;

@Service("historialVentaService")
public class HistorialVentaServiceImpl implements HistorialVentaService{
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
	@Override
	public List<HistorialVenta> listAllHistorialVentas() {
		// TODO Auto-generated method stub
		return hvJPA.findAll();
	}

	@Override
	public HistorialVenta addHistorialVenta(HistorialVenta hventa) {
		// TODO Auto-generated method stub
		return hvJPA.save(hventa);
	}

	@Override
	public void removeHistorialVenta(int id) {
		// TODO Auto-generated method stub
		hvJPA.deleteById(id);
	}

	@Override
	public HistorialVenta updateHistorialVenta(HistorialVenta hventa) {
		// TODO Auto-generated method stub
		return hvJPA.save(hventa);
	}

	@Override
	public HistorialVenta consultarHistorialVenta(int id) {
		// TODO Auto-generated method stub
		return hvJPA.findByIdHistorial(id);
	}

}
