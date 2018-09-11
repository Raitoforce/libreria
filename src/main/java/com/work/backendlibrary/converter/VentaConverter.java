package com.work.backendlibrary.converter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.BloqueFolio;
import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.model.VentaModel;
import com.work.backendlibrary.service.BloqueFolioService;
import com.work.backendlibrary.service.EscuelaService;
import com.work.backendlibrary.service.ProfesorService;
import com.work.backendlibrary.service.VentaService;

@Component("ventaConverter")
public class VentaConverter {
	@Autowired
	@Qualifier("bloqueFolioService")
	BloqueFolioService bfService;
	@Autowired
	@Qualifier("escuelaServiceImpl")
	EscuelaService escuelaService;
	@Autowired
	@Qualifier("profesorServiceImpl")
	ProfesorService profesorService;
	
	@Autowired
	@Qualifier("historialVentaConverter")
	HistorialVentaConverter hvConverter;
	
	@Autowired
	@Qualifier("ventaService")
	VentaService ventaService;
	
	public Venta model2Entity(VentaModel vm){
		Venta v=new Venta();
		BloqueFolio bloque=bfService.isInRangeAndVendedor(vm.getVendedor_clave(),Integer.parseInt(vm.getFolio()));
		Escuela escuela=escuelaService.consultarEscuela(vm.getEscuela_clave());
		Profesor profesor=profesorService.consultarProfesor(vm.getIdprofesor());
		v.setBloqueFolio(bloque);
		v.setEscuela(escuela);
		v.setProfesor(profesor);
		v.setComisionDirector(vm.getComision_director());
		v.setComisionProfesor(vm.getComision_profesor());
		v.setComisionVendedor(vm.getComision_vendedor());
		v.setFecha(vm.getFecha());
		v.setFolio(vm.getFolio());
		if(vm.getLideres()!=null){
			v.setLideres(vm.lider2entity());
		}
		if(vm.getPedidos()!=null)
			v.setPedidos(hvConverter.model2ListEntity(vm.getPedidos(), v));
		return v;
	}
	
	public Venta appendPedidos(String folio,LinkedHashSet<HistorialVentaModel> vpedidos){
		Venta venta=ventaService.consultarVenta(folio);
		LinkedHashSet<HistorialVenta> pedidosN=hvConverter.model2ListEntity(vpedidos, venta);
		LinkedHashSet<HistorialVenta> pedidos= new LinkedHashSet<HistorialVenta>(venta.getPedidos());
		for (HistorialVenta pedido : pedidosN)
			pedidos.add(pedido);
		venta.setPedidos(pedidos);
		return venta;
	}
	
}
