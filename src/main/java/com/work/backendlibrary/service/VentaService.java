package com.work.backendlibrary.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.model.VentaModel;

public interface VentaService {
	public abstract List<Venta> listAllVentas();
	public abstract	Venta addVenta(VentaModel venta);
	public abstract void removeVenta(String folio);
	public abstract Venta updateVenta(VentaModel venta);
	public abstract Venta consultarVenta(String folio);
	public abstract Venta consultarVentaByVendedorAndTemporada(String clave,int id);
	public abstract Venta appendPedidos(String folio,LinkedHashSet<HistorialVentaModel> pedidos);
	public abstract boolean VentaIsOnDB(String folio);
	public abstract boolean VentaHasConfirmed(String folio);
	public abstract List<Venta> consultarByTemporadaAndHacienda(int idtemporada,int hacienda);
}
