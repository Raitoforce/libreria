package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.HistorialVentaModel;

import java.util.List;

public interface HistorialVentaService {
    public abstract List<HistorialVenta> listAllHistorialVentas();

    public abstract List<HistorialVenta> listAllHistorialVentasByHacienda(int hacienda, int temporada);

    public abstract HistorialVenta addHistorialVenta(HistorialVentaModel hventa);

    public abstract void removeHistorialVenta(int id);

    public abstract HistorialVenta updateHistorialVenta(HistorialVentaModel hventa);

    public abstract HistorialVenta consultarHistorialVenta(int id);

    public abstract HistorialVenta updateInventario(HistorialVenta historialVenta);

    public abstract List<HistorialVenta> consultarByVenta(String idVenta);

    public abstract Integer getMaximo(String folio);

    public abstract List<HistorialVenta> consultarByNumResurtido(int numresurtido, String folio);

    public abstract void eliminarByResurtido(String folio, int numresurtido);

    public abstract boolean numresurtidoHasConfirmed(String folio, int numresurtido);

}
