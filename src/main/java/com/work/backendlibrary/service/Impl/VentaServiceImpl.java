package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.repository.VentaJPARepository;
import com.work.backendlibrary.service.VentaService;

@Service("ventaService")
public class VentaServiceImpl implements VentaService{
	@Autowired
	@Qualifier("ventaJPARepository")
	VentaJPARepository ventaJPA;
	
	@Override
	public List<Venta> listAllVentas() {
		// TODO Auto-generated method stub
		return ventaJPA.findAll();
	}

	@Override
	public Venta addVenta(Venta venta) {
		// TODO Auto-generated method stub
		return ventaJPA.save(venta);
	}

	@Override
	public void removeVenta(String folio) {
		// TODO Auto-generated method stub
		ventaJPA.deleteById(folio);
	}

	@Override
	public Venta updateVenta(Venta venta) {
		// TODO Auto-generated method stub
		return ventaJPA.save(venta);
	}

	@Override
	public Venta consultarVenta(String folio) {
		// TODO Auto-generated method stub
		return ventaJPA.findByFolio(folio);
	}
}
