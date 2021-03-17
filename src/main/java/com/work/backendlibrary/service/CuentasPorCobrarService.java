package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.model.CuentasProfesorModel;
import com.work.backendlibrary.model.CuentasVEModel;

public interface CuentasPorCobrarService {
	List<CuentasPorCobrar> listAllCuentas();
	CuentasPorCobrar addCuenta(CuentasPorCobrar cuenta);
	void removeCuenta(int idmoviento);
	CuentasPorCobrar updateCuenta(CuentasPorCobrar cuenta);
	CuentasPorCobrar consultarCuenta(int idmovimiento);
	
	void insertarMonto(float monto, String claveV, String claveE, int idprofesor, int idtemporada);
	
	void abonoRapido(float monto, String folio);
	float consultaCuentaByVenta(String folio, int idtemporada);
	List<CuentasProfesorModel> consultaCuentaByProfesor(String claveE, String claveV, int idtemporada);
	List<CuentasVEModel> consultaCuentaByEscuela(String claveV, int idtemporada);
	List<CuentasVEModel> consultaCuentaByVendedor(int idtemporada);
}
