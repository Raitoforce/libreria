package com.work.backendlibrary.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.repository.CuentasPorCobrarJPARepository;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.service.CuentasPorCobrarService;
import com.work.backendlibrary.service.HistorialVentaService;

@Service("cuentasPorCobrarService")
public class CuentasPorCobrarServiceImpl implements CuentasPorCobrarService{
	@Autowired
	@Qualifier("cuentasPorCobrarJPARepository")
	CuentasPorCobrarJPARepository cpcJPA;
	
	@Autowired
	@Qualifier("historialVentaJPARepository")
	HistorialVentaJPARepository hvJPA;
	
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
	public CuentasPorCobrar insertarMonto(int monto,String claveV,String claveE,int idprofesor,int idtemporada){
		// TODO Auto-generated method stub
		CuentasPorCobrar cpc = new CuentasPorCobrar();
		cpc.setFecha(new Date(System.currentTimeMillis()));
		cpc.setPago(monto);
		List<HistorialVenta> historialVentas = null;//hvJPA.(claveV, claveE, idprofesor);
		float total=0;
		for (HistorialVenta historialVenta : historialVentas) {
			List<CuentasPorCobrar> cuentas=cpcJPA.findByHistorialVentaIdHistorial(historialVenta.getIdHistorial());
			for (CuentasPorCobrar cuenta : cuentas) {
				total+=cuenta.getPago();
			}
			if (total+monto<=historialVenta.getPedidos()*historialVenta.getPrecioventa()) {
				cpc.setIdMovimiento(historialVenta.getIdHistorial());
				break;
			}
		}
		return cpcJPA.save(cpc);
	}

}
