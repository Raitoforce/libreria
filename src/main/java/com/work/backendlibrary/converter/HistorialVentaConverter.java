package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.service.LibroService;
import com.work.backendlibrary.service.VentaService;

@Component("historialVentaConverter")
public class HistorialVentaConverter {
	@Autowired
	@Qualifier("libroServiceImpl")
	LibroService libService;
	@Autowired
	@Qualifier("ventaService")
	VentaService vService;
	
	
	public HistorialVenta model2entity(HistorialVentaModel hvm){
		HistorialVenta hv=new HistorialVenta();
		hv.setLibro(libService.consultarLibro(hvm.getLibro_clave()));
		hv.setVenta(vService.consultarVenta(hvm.getVenta_folio()));
		hv.setEntregados(hvm.getEntregados());
		hv.setFechaConfirmacion(hvm.getFecha_confirmacion());
		hv.setPedidos(hvm.getPedidos());
		hv.setFechaSolicitud(new Date(System.currentTimeMillis()));
		hv.setIdHistorial(hvm.getIdHistorial());
		hv.setPrecioventa(hvm.getPrecioventa());
		hv.setNumresurtido(hvm.getNumresurtido());
		hv.setMotivo(hvm.getMotivo());
		hv.setTipoMovimiento(hvm.getTipo_movimiento());
		return hv;
	}
	
	public Set<HistorialVenta> model2ListEntity(Set<HistorialVentaModel> vpedidos, Venta venta){
		Set<HistorialVenta> hvs=new HashSet<>();
		for (HistorialVentaModel historialVentam : vpedidos){
			HistorialVenta hv=model2entity(historialVentam);
			hv.setVenta(venta);
			hvs.add(hv);
		}
		
		return hvs;
	}
	
}
