package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.HistorialVentaModel;

public interface HistorialVentaService {
	public abstract List<HistorialVenta> listAllHistorialVentas();
	public abstract	HistorialVenta addHistorialVenta(HistorialVentaModel hventa);
	public abstract void removeHistorialVenta(int id);
	public abstract HistorialVenta updateHistorialVenta(HistorialVentaModel hventa);
	public abstract HistorialVenta consultarHistorialVenta(int id);
}
