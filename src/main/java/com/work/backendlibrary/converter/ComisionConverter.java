package com.work.backendlibrary.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Comision;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.LideresComisiones;
import com.work.backendlibrary.entity.LideresComisionesPK;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Temporada;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.ComisionModel;
import com.work.backendlibrary.repository.ComisionJPARepository;
import com.work.backendlibrary.repository.DirectorJPARepository;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.repository.LideresComisionesJPARepository;
import com.work.backendlibrary.repository.ProfesorJPARepository;
import com.work.backendlibrary.repository.TemporadaJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;
import com.work.backendlibrary.repository.VentaJPARepository;

@Component("comisionConverter")
public class ComisionConverter {
	@Autowired
	@Qualifier("temporadaJPARepository")
	TemporadaJPARepository tJPA;
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vJPA;
	
	@Autowired
	@Qualifier("lideresComisionesJPARepository")
	LideresComisionesJPARepository clJPA;
	
	@Autowired
	@Qualifier("directorJPARepository")
	DirectorJPARepository dJPA;
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;
	
	@Autowired
	@Qualifier("comisionJPARepository")
	ComisionJPARepository cmJPA;
	
	@Autowired
	@Qualifier("ventaJPARepository")
	VentaJPARepository ventaJPA;
	
	public Comision entity2modelD(ComisionModel cm){
		Comision c=new Comision();
		Director d=dJPA.findByIddirector(cm.getDirector());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());
		c.setDirector(d);
		c.setTemporada(t);
		c.setFecha(new Date(System.currentTimeMillis()));
		c.setMonto(cm.getMonto());
		c.setTipo("DIRECTOR");
		return c;
	}
	
	public Comision entity2modelV(ComisionModel cm){
		Comision c=new Comision();
		Vendedor v=vJPA.findByClave(cm.getVendedor());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());
		c.setVendedor(v);
		c.setTemporada(t);
		c.setFecha(new Date(System.currentTimeMillis()));
		c.setMonto(cm.getMonto());
		c.setTipo("VENDEDOR");
		return c;
	}
	
	//Metodo para setear comisiones por venta y lider
	public void entity2modelP(ComisionModel cm){
		Comision c=new Comision();
		Profesor profesor = pJPA.findByIdprofesor(cm.getLider());
		Temporada t=tJPA.findByIdtemporada(cm.getTemporada());

		LideresComisiones lider = null;
		Venta venta =null;
		float monto=0;
		while(cm.getMonto()!=0){
			c=new Comision();
			venta = obtenerVentaActual(cm.getLider(), cm.getTemporada());
			if(venta==null)break;
			c.setTemporada(t);
			if(cm.getMonto()<=venta.getLiderComisionTotal(cm.getLider())){
				monto=cm.getMonto();
				cm.setMonto(0);
			}else{
				monto=venta.getLiderComisionTotal(cm.getLider());
				cm.setMonto(cm.getMonto()-venta.getLiderComisionTotal(cm.getLider()));
			}
			c.setMonto(monto);
			c.setTipo("LIDER");
			lider = clJPA.findByIdProfesorAndIdVenta(cm.getLider(), venta.getFolio());
			c.setLider(lider);
			c.setFecha(new Date(System.currentTimeMillis()));
			cmJPA.save(c);
		}
	}
	
	public Venta obtenerVentaActual(int idprofesor,int idtemporada){
		float totalC=0;
		float totalP=0;
		Venta ventar=null;
		for (Comision comision:cmJPA.findByTemporadaIdtemporadaAndLiderLiderIdprofesorAndTipo(idtemporada,idprofesor,"LIDER")) {
			totalC+=comision.getMonto();
		}
		for (Venta venta : ventaJPA.findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporada(idprofesor, idtemporada)) {
			totalP+=venta.getLiderComisionTotal(idprofesor);
			ventar = venta;
			if(venta.getLiderComisionTotal(idprofesor)==0)continue; //No asignar a las ventas 0
			if (totalC<totalP) {
				break;
			}
		}
		return ventar;
	}
}
