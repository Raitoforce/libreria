package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.CuentasPorCobrar;

public interface CuentasPorCobrarService {
	public abstract List<CuentasPorCobrar> listAllCuentas();
	public abstract	CuentasPorCobrar addCuenta(CuentasPorCobrar cuenta);
	public abstract void removeCuenta(int idmoviento);
	public abstract CuentasPorCobrar updateCuenta(CuentasPorCobrar cuenta);
	public abstract CuentasPorCobrar consultarCuenta(int idmovimiento);
}
