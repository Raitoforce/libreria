package com.work.backendlibrary.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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

@Component("cuentasConverter")
public class CuentasConverter {
	@Autowired
	@Qualifier("cuentasPorCobrarJPARepository")
	CuentasPorCobrarJPARepository cpcJPA;
	
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vendedorJPA;
	
	@Autowired
	@Qualifier("escuelaJPARepository")
	EscuelaJPARepository eJPA;
	
	
	@Autowired
	@Qualifier("profesorJPARepository")
	ProfesorJPARepository pJPA;
	
	public CuentasVEModel CuentasVendedor(String clave, int idtemporada){
		CuentasVEModel cuenta=new CuentasVEModel();
		Vendedor vendedor=vendedorJPA.findByClave(clave);
		List<CuentasPorCobrar> cuentasPorCobrar = cpcJPA.findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(clave, idtemporada);
		List<HistorialVenta> pedidos=hvJPA.findByVentaBloqueFolioVendedorClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(clave, idtemporada);
		float deuda=0;
		float pagado=0;
		for (HistorialVenta pedido : pedidos) {
			deuda+=pedido.CalcularDeuda();
		}
		for (CuentasPorCobrar cuentas : cuentasPorCobrar) {
			pagado+=cuentas.getPago();
		}
		cuenta.setClave(clave);
		cuenta.setDeuda(deuda);
		cuenta.setPagado(pagado);
		cuenta.setNombre(vendedor.getNombre()+" "+vendedor.getApellidos());
		cuenta.setRestante(deuda-pagado);
		return cuenta;
	}
	
	public CuentasVEModel CuentasEscuela(String claveE,String claveV, int idtemporada){
		CuentasVEModel cuenta=new CuentasVEModel();
		Escuela escuela= eJPA.findByClave(claveE);
		List<CuentasPorCobrar> cuentasPorCobrar = cpcJPA.findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaEscuelaClaveAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(claveV, claveE, idtemporada);
		List<HistorialVenta> pedidos=hvJPA.findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(claveV, claveE, idtemporada);
		float deuda=0;
		float pagado=0;
		for (HistorialVenta pedido : pedidos) {
			deuda+=pedido.CalcularDeuda();
		}
		for (CuentasPorCobrar cuentas : cuentasPorCobrar) {
			pagado+=cuentas.getPago();
		}
		cuenta.setClave(claveE);
		cuenta.setDeuda(deuda);
		cuenta.setPagado(pagado);
		cuenta.setNombre(escuela.getNombre());
		cuenta.setRestante(deuda-pagado);
		return cuenta;
	}
	
	public CuentasProfesorModel CuentasProfesor(String claveE,String claveV,int idprofesor,int idtemporada){
		CuentasProfesorModel cuenta= new CuentasProfesorModel();
		Profesor profesor= pJPA.findByIdprofesor(idprofesor);
		List<CuentasPorCobrar> cuentasPorCobrar = cpcJPA.findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaEscuelaClaveAndHistorialVentaVentaProfesorIdprofesorAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(claveV, claveE, idprofesor, idtemporada);
		List<HistorialVenta> pedidos=hvJPA.findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaProfesorIdprofesorAndVentaBloqueFolioFolioIdtemporadaIdtemporada(claveV, claveE, idprofesor, idtemporada);
		float deuda=0;
		float pagado=0;
		for (HistorialVenta pedido : pedidos) {
			deuda+=pedido.CalcularDeuda();
		}
		for (CuentasPorCobrar cuentas : cuentasPorCobrar) {
			pagado+=cuentas.getPago();
		}
		cuenta.setIdprofesor(idprofesor);
		cuenta.setDeuda(deuda);
		cuenta.setPagado(pagado);
		cuenta.setNombre(profesor.getNombre()+" "+profesor.getApellidos());
		cuenta.setRestante(deuda-pagado);
		return cuenta;
	}
}
