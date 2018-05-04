package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.HistorialVenta;

public interface HistorialVentaService {
	public abstract List<HistorialVenta> listAllHistorialVentas();
	public abstract	HistorialVenta addHistorialVenta(HistorialVenta hventa);
	public abstract void removeHistorialVenta(int id);
	public abstract HistorialVenta updateHistorialVenta(HistorialVenta hventa);
	public abstract HistorialVenta consultarHistorialVenta(int id);
}
