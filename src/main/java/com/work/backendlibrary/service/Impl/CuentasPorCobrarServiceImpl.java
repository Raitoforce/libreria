package com.work.backendlibrary.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.CuentasConverter;
import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.entity.Escuela;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Profesor;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.CuentasProfesorModel;
import com.work.backendlibrary.model.CuentasVEModel;
import com.work.backendlibrary.repository.CuentasPorCobrarJPARepository;
import com.work.backendlibrary.repository.EscuelaJPARepository;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.repository.ProfesorJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;
import com.work.backendlibrary.service.CuentasPorCobrarService;

@Service("cuentasPorCobrarService")
public class CuentasPorCobrarServiceImpl implements CuentasPorCobrarService{
	@Autowired
	@Qualifier("cuentasPorCobrarJPARepository")
	CuentasPorCobrarJPARepository cpcJPA;
	
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
	@Autowired
	@Qualifier("cuentasConverter")
	CuentasConverter cuentasC;
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vendedorJPA;
	
	@Autowired
	@Qualifier("escuelaJPARepository")
	EscuelaJPARepository eJPA;
	
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;
	
	@Override
	public List<CuentasPorCobrar> listAllCuentas() {
		// TODO Auto-generated method stub
		return cpcJPA.findAll();
	}

	@Override
	public CuentasPorCobrar addCuenta(CuentasPorCobrar cuenta) {
		// TODO Auto-generated method stub
		return cpcJPA.save(cuenta);
	}

	@Override
	public void removeCuenta(int idmoviento) {
		// TODO Auto-generated method stub
		cpcJPA.deleteById(idmoviento);
	}

	@Override
	public CuentasPorCobrar updateCuenta(CuentasPorCobrar cuenta) {
		// TODO Auto-generated method stub
		return cpcJPA.save(cuenta);
	}

	@Override
	public CuentasPorCobrar consultarCuenta(int idmovimiento) {
		// TODO Auto-generated method stub
		return cpcJPA.findByIdMovimiento(idmovimiento);
	}

	@Override
	public void insertarMonto(float monto,String claveV,String claveE,int idprofesor,int idtemporada){
		// TODO Auto-generated method stub
		List<CuentasPorCobrar> cuentasPorCobrar= null;
		CuentasPorCobrar cpc = null;
		Date currentDate=new Date(System.currentTimeMillis());
		//Se obtienen los pedidos por  Temporada,Vendedor,Escuela,Profesor
		float total;
		List<HistorialVenta> historialVentas = hvJPA.findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaProfesorIdprofesorAndVentaBloqueFolioFolioIdtemporadaIdtemporada(claveV, claveE, idprofesor, idtemporada);
		for (HistorialVenta historialVenta : historialVentas){
			total=0;
			if(monto==0)break;
			cpc=new CuentasPorCobrar();
			cuentasPorCobrar= cpcJPA.findByHistorialVentaIdHistorial(historialVenta.getIdHistorial());
			for (CuentasPorCobrar cuenta : cuentasPorCobrar) {
				total+=cuenta.getPago();
			} //Se hace la sumatoria
			
			if(total==historialVenta.CalcularDeuda())continue;
			
			if(total+monto<=historialVenta.CalcularDeuda()){
				cpc.setPago(monto);
				monto=0;
			}else{
				monto-=(historialVenta.CalcularDeuda()-total);
				cpc.setPago(historialVenta.CalcularDeuda()-total);
			}
			cpc.setFecha(currentDate);
			cpc.setHistorialVenta(historialVenta); 
			cpcJPA.save(cpc);
		}
	}

	@Override
	public float consultaCuentaByVenta(String folio, int idtemporada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public	List<CuentasProfesorModel> consultaCuentaByProfesor(String claveE,String claveV,int idtemporada) {
		// TODO Auto-generated method stub
		List<Profesor> profesores=pJPA.findAll();
		List<CuentasProfesorModel> cuentas=new ArrayList<>();
		CuentasProfesorModel cpm =null;
		for (Profesor profesor : profesores){
			cpm = cuentasC.CuentasProfesor(claveE, claveV,profesor.getIdprofesor(), idtemporada);
			if(cpm.getDeuda()>0)
				cuentas.add(cpm);
		}
		return cuentas;
	}

	@Override
	public List<CuentasVEModel> consultaCuentaByEscuela(String claveV,int idtemporada) {
		// TODO Auto-generated method stub
		List<CuentasVEModel> cuentas=new ArrayList<>();
		List<Escuela> escuelas=eJPA.findAll();
		CuentasVEModel cvem=null;
		for (Escuela escuela : escuelas) {
			cvem = cuentasC.CuentasEscuela(escuela.getClave(), claveV, idtemporada);
			if(cvem.getDeuda()>0)
				cuentas.add(cvem);
		}
		return cuentas;
	}

	@Override
	public List<CuentasVEModel> consultaCuentaByVendedor(int idtemporada) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<CuentasVEModel> cuentas=new ArrayList<>();
		List<Vendedor> vendedores=vendedorJPA.findAll();
		CuentasVEModel cvem=null;
		for (Vendedor vendedor: vendedores) {
			cvem=cuentasC.CuentasVendedor(vendedor.getClave(), idtemporada);
			if(cvem.getDeuda()>0)
				cuentas.add(cvem);
		}
		return cuentas;
	}

}
