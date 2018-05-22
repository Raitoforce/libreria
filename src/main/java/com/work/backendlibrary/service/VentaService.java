package com.work.backendlibrary.service;

import java.util.List;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.VentaModel;

public interface VentaService {
	public abstract List<Venta> listAllVentas();
	public abstract	Venta addVenta(VentaModel venta);
	public abstract void removeVenta(String folio);
	public abstract Venta updateVenta(VentaModel venta);
	public abstract Venta consultarVenta(String folio);
	public abstract Venta consultarVentaByVendedorAndTemporada(String clave,int id);
	
	public abstract boolean VentaIsOnDB(String folio);
}
