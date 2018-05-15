package com.work.backendlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.HistorialVenta;
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
		hv.setFechaSolicitud(hvm.getFecha_solicitud());
		hv.setIdHistorial(hvm.getIdHistorial());
		hv.setMotivo(hvm.getMotivo());
		hv.setTipoMovimiento(hvm.getTipo_movimiento());
		return hv;
	}
}