package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.HistorialVentaModel;

import java.util.List;

public interface HistorialVentaService {
    List<HistorialVenta> listAllHistorialVentas();

    List<HistorialVenta> listAllHistorialVentasByHacienda(int hacienda, int temporada);

    HistorialVenta addHistorialVenta(HistorialVentaModel hventa);

    void removeHistorialVenta(int id);

    HistorialVenta updateHistorialVenta(HistorialVentaModel hventa);

    HistorialVenta consultarHistorialVenta(int id);

    HistorialVenta updateInventario(HistorialVenta historialVenta);

    List<HistorialVenta> consultarByVenta(String idVenta);

    Integer getMaximo(String folio);

    List<HistorialVenta> consultarByNumResurtido(int numresurtido, String folio);

    void eliminarByResurtido(String folio, int numresurtido);

    boolean numresurtidoHasConfirmed(String folio, int numresurtido);

}
