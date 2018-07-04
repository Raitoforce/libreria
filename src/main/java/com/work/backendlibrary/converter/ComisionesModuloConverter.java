package com.work.backendlibrary.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.ComisionesVistaModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;
import com.work.backendlibrary.repository.VentaJPARepository;

@Component("comisionesModuloConverter")
public class ComisionesModuloConverter {
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vJPA;
	
	@Autowired
	@Qualifier("directorJPARepository")
	DirectorJPARepository dJPA;
	
	@Autowired
	@Qualifier("comisionJPARepository")
	ComisionJPARepository cJPA;
	
	@Autowired
	@Qualifier("ventaJPARepository")
	VentaJPARepository ventaJPA;
	
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
	public ComisionesVistaModel cuentaVendedor(String clave,int idtemporada){
		ComisionesVistaModel cvm=new ComisionesVistaModel();
		Vendedor vendedor=vJPA.findByClave(clave);
		List<Comision> comisiones=cJPA.findByTemporadaIdtemporadaAndVendedorClaveAndTipo(idtemporada, clave,"VENDEDOR");
		List<Venta> ventas=ventaJPA.findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporada(clave, idtemporada);
		List<HistorialVenta> pedidos=null;
		float total=0;
		float deuda=0;
		for (Venta venta : ventas) {
			pedidos=hvJPA.findByVentaFolio(venta.getFolio());
			for (HistorialVenta pedido : pedidos) {
				if(pedido.getPrecioventa()!=0)
					deuda+=pedido.getPedidos()*venta.getComisionVendedor();
			}
		}
		for (Comision comision : comisiones) {
			total+=comision.getMonto();
		}
		
		cvm.setNombre("("+vendedor.getClave()+")"+vendedor.getNombre()+" "+vendedor.getApellidos());
		cvm.setDeuda(deuda);
		cvm.setPagado(total);
		cvm.setRestante(deuda-total);
		return cvm;
	}
	
	public ComisionesVistaModel cuentaDirector(int iddirector,int idtemporada){
		ComisionesVistaModel cvm=new ComisionesVistaModel();
		Director director=dJPA.findByIddirector(iddirector);
		List<Comision> comisiones=cJPA.findByTemporadaIdtemporadaAndDirectorIddirectorAndTipo(idtemporada, iddirector,"DIRECTOR");
		List<Venta> ventas=ventaJPA.findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporada(iddirector, idtemporada);
		float total=0;
		float deuda=0;
		List<HistorialVenta> pedidos=null;
		for (Venta venta : ventas) {
			pedidos=hvJPA.findByVentaFolio(venta.getFolio());
			for (HistorialVenta pedido : pedidos) {
				if(pedido.getPrecioventa()!=0)
					deuda+=pedido.getPedidos()*venta.getComisionDirector();
			}
		}
		for (Comision comision : comisiones) {
			total+=comision.getMonto();
		}
		cvm.setNombre(director.getNombre()+" "+director.getApellidos());
		cvm.setDeuda(deuda);
		cvm.setPagado(total);
		cvm.setRestante(deuda-total);
		return cvm;
	}
}
