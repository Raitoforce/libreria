package com.work.backendlibrary.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.ComisionesVistaModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.repository.LideresComisionesJPARepository;
import com.work.backendlibrary.repository.ProfesorJPARepository;
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
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;
	
	@Autowired
	@Qualifier("lideresComisionesJPARepository")
	LideresComisionesJPARepository lcJPA;
	
	public ComisionesVistaModel cuentaVendedor(String clave,int idtemporada , int hacienda){
		ComisionesVistaModel cvm=new ComisionesVistaModel();
		Vendedor vendedor=vJPA.findByClave(clave);
		List<Comision> comisiones=cJPA.findByTemporadaIdtemporadaAndVendedorClaveAndTipo(idtemporada, clave,"VENDEDOR");
		List<Venta> ventas=ventaJPA.findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(clave, idtemporada, hacienda);
		List<HistorialVenta> pedidos=null;
		float total=0;
		float deuda=0;
		for (Venta venta : ventas) {
			pedidos=hvJPA.findByVentaFolio(venta.getFolio());
			for (HistorialVenta pedido : pedidos) {
				try{
					if(pedido.getPrecioventa()!=0)
						deuda+=(pedido.getPedidos()*venta.getComisionVendedor());
				}catch(Exception e){
					deuda+=0;
				}
			}
		}
		if(comisiones!=null){
			for (Comision comision : comisiones) {
				total+=comision.getMonto();
			}
		}
		cvm.setNombre(vendedor.getNombre()+" "+vendedor.getApellidos());
		cvm.setDeuda(deuda);
		cvm.setPagado(total);
		cvm.setRestante(deuda-total);
		cvm.setClave(clave);
		return cvm;
	}
	
	public ComisionesVistaModel cuentaDirector(int iddirector,int idtemporada , int hacienda){
		ComisionesVistaModel cvm=new ComisionesVistaModel();
		Director director=dJPA.findByIddirector(iddirector);
		List<Comision> comisiones=cJPA.findByTemporadaIdtemporadaAndDirectorIddirectorAndTipo(idtemporada, iddirector,"DIRECTOR");
		List<Venta> ventas=ventaJPA.findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(iddirector, idtemporada, hacienda);
		float total=0;
		float deuda=0;
		List<HistorialVenta> pedidos=null;
		for (Venta venta : ventas) {
			pedidos=hvJPA.findByVentaFolio(venta.getFolio());
			for (HistorialVenta pedido : pedidos) {
				if(pedido.getPrecioventa()!=0){
					deuda+=(pedido.getPedidos()*venta.getComisionDirector());
					//System.out.println(deuda+":"+venta.getFolio()+":"+iddirector);
				}
			}
		}
		if(comisiones!=null){
			for (Comision comision : comisiones) {
				total+=comision.getMonto();
			}
		}
		cvm.setNombre(director.getNombre()+" "+director.getApellidos());
		cvm.setDeuda(deuda);
		cvm.setPagado(total);
		cvm.setRestante(deuda-total);
		cvm.setIddirector(iddirector);
		return cvm;
	}
	
	public ComisionesVistaModel cuentaLider(int idprofesor,int idtemporada, int hacienda){
		ComisionesVistaModel cvm=new ComisionesVistaModel();
		Profesor profesor = pJPA.findByIdprofesor(idprofesor);
		List<Comision> comisiones=cJPA.findByTemporadaIdtemporadaAndLiderLiderIdprofesorAndTipo(idtemporada, idprofesor,"LIDER");
		List<Venta> ventas=ventaJPA.findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(idprofesor, idtemporada, hacienda);
		float total=0;
		float deuda=0;
		List<HistorialVenta> pedidos=null;
		for (Venta venta : ventas) {
			pedidos=hvJPA.findByVentaFolio(venta.getFolio());
			for (HistorialVenta pedido : pedidos) {
				if(pedido.getPrecioventa()!=0){
					deuda+=(pedido.getPedidos()*venta.getLiderComision(idprofesor));
					System.out.println(deuda+":"+venta.getFolio()+":"+idprofesor);
				}
			}
		}
		if(comisiones!=null){
			for (Comision comision : comisiones) {
				total+=comision.getMonto();
			}
		}
		cvm.setNombre(profesor.getNombre()+" "+profesor.getApellidos());
		cvm.setDeuda(deuda);
		cvm.setPagado(total);
		cvm.setRestante(deuda-total);
		cvm.setIdprofesor(idprofesor);
		return cvm;
	}
}
