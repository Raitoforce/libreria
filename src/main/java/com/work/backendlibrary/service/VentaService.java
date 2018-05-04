package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Venta;

public interface VentaService {
	public abstract List<Venta> listAllVentas();
	public abstract	Venta addVenta(Venta venta);
	public abstract void removeVenta(String folio);
	public abstract Venta updateVenta(Venta venta);
	public abstract Venta consultarVenta(String folio);
}
