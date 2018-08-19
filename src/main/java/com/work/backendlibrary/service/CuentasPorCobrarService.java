package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.model.CuentasProfesorModel;
import com.work.backendlibrary.model.CuentasVEModel;

public interface CuentasPorCobrarService {
	public abstract List<CuentasPorCobrar> listAllCuentas();
	public abstract	CuentasPorCobrar addCuenta(CuentasPorCobrar cuenta);
	public abstract void removeCuenta(int idmoviento);
	public abstract CuentasPorCobrar updateCuenta(CuentasPorCobrar cuenta);
	public abstract CuentasPorCobrar consultarCuenta(int idmovimiento);
	
	public abstract void insertarMonto(float monto,String claveV, String claveE,int idprofesor,int idtemporada);
	
	public abstract void abonoRapido(float monto,String folio);
	public abstract float consultaCuentaByVenta(String folio,int idtemporada);
	public abstract List<CuentasProfesorModel> consultaCuentaByProfesor(String claveE,String claveV,int idtemporada);
	public abstract List<CuentasVEModel> consultaCuentaByEscuela(String claveV,int idtemporada);
	public abstract List<CuentasVEModel> consultaCuentaByVendedor(int idtemporada);
}
