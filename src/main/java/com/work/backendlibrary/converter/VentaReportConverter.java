package com.work.backendlibrary.converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.PedidosReportModel;
import com.work.backendlibrary.model.VentaReportModel;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;

@Component("ventaReportConverter")
public class VentaReportConverter {
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
	public VentaReportModel entity2model(Venta venta){
		VentaReportModel ventam=new VentaReportModel();
		ventam.setFecha((Date) venta.getFecha());
		ventam.setProfesor_nombre(venta.getProfesor().getNombre()+" "+venta.getProfesor().getApellidos());
		ventam.setProfesor_telefono(venta.getProfesor().getTelefono());
		ventam.setEscuela_nombre(venta.getEscuela().getNombre()+" ("+venta.getEscuela().getClave()+") Turno:"+venta.getEscuela().getTurno());
		ventam.setEscuela_domicilio(venta.getEscuela().getDireccion()+" "+venta.getEscuela().getColonia()+"("+venta.getEscuela().getCodigoPostal()+")");
		ventam.setFolio(venta.getFolio());
		ventam.setMunicipio_escuela(venta.getEscuela().getMunicipio());	
		return ventam;
	}
	
	public VentaReportModel entity2modelp(Venta venta,int numresurtido){
		VentaReportModel ventam=entity2model(venta);
		ventam.setFecha(hvJPA.getDate(venta.getFolio(),numresurtido));
		return ventam;
	}
	
	public List<PedidosReportModel> entity2modelPedidos(List<HistorialVenta> pedidos){
		List<PedidosReportModel> pedidosm=new ArrayList<PedidosReportModel>();
		PedidosReportModel pedidom;
		for (HistorialVenta pedido:pedidos) {
			pedidom=new PedidosReportModel();
			pedidom.setCantidad(pedido.getPedidos());
			pedidom.setFechap(pedido.getFechaSolicitud());
			pedidom.setLibro_grado(pedido.getLibro().getCurso());
			pedidom.setLibro_importe(pedido.getPedidos()*pedido.getPrecioventa());
			pedidom.setLibro_nombre(pedido.getLibro().getTitulo());
			pedidom.setLibro_precio(pedido.getLibro().getPrecio());
			pedidom.setPrecioventa(pedido.getPrecioventa());
			pedidosm.add(pedidom);
		}
		return pedidosm;
	}
	
}
