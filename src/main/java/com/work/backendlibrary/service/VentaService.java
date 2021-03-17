package com.work.backendlibrary.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.model.VentaModel;

public interface VentaService {
	List<Venta> listAllVentas();
	Venta addVenta(VentaModel venta);
	void removeVenta(String folio);
	Venta updateVenta(VentaModel venta);
	Venta consultarVenta(String folio);
	Venta consultarVentaByVendedorAndTemporada(String clave, int id);
	Venta appendPedidos(String folio, LinkedHashSet<HistorialVentaModel> pedidos);
	boolean VentaIsOnDB(String folio);
	boolean VentaHasConfirmed(String folio);
	List<Venta> consultarByTemporadaAndHacienda(int idtemporada, int hacienda);
}
