package com.work.backendlibrary.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.VentaConverter;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.model.VentaModel;
import com.work.backendlibrary.repository.VentaJPARepository;
import com.work.backendlibrary.service.VentaService;

@Service("ventaService")
public class VentaServiceImpl implements VentaService{
	@Autowired
	@Qualifier("ventaJPARepository")
	VentaJPARepository ventaJPA;
	@Autowired
	@Qualifier("ventaConverter")
	VentaConverter vConverter;
	
	@Override
	public List<Venta> listAllVentas() {
		// TODO Auto-generated method stub
		return ventaJPA.findAll();
	}

	@Override
	public Venta addVenta(VentaModel ventam) {
		// TODO Auto-generated method stub
		Venta  venta= vConverter.model2Entity(ventam);
		return ventaJPA.save(venta);
	}

	@Override
	public void removeVenta(String folio) {
		// TODO Auto-generated method stub
		ventaJPA.deleteById(folio);
	}

	@Override
	public Venta updateVenta(VentaModel ventam) {
		// TODO Auto-generated method stub
		Venta venta=vConverter.model2Entity(ventam);
		return ventaJPA.save(venta);
	}

	@Override
	public Venta consultarVenta(String folio) {
		// TODO Auto-generated method stub
		return ventaJPA.findByFolio(folio);
	}

	@Override
	public Venta consultarVentaByVendedorAndTemporada(String clave, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean VentaIsOnDB(String folio) {
		// TODO Auto-generated method stub
		if(ventaJPA.findByFolio(folio)!=null)
			return true;
		return false;
	}

	@Override
	public Venta appendPedidos(String folio,List<HistorialVentaModel> pedidos) {
		Venta venta=vConverter.appendPedidos(folio, pedidos);
		return ventaJPA.save(venta);
	}
}
