package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.LibroStockModel;

import java.util.List;

public interface InventarioService {
    List<HistorialVenta> getPedidosPendientes();

    List<HistorialVenta> getPedidosPendientesHacienda(int hacienda, int temporada);

    void confirmarPedido(int idHistorial, int entregados, int hacienda);

    void generarReporte(String folio);

    void generarReportePedido(String folio, int numresurtido);

    int getStockActualTotal(String clave);

    List<LibroStockModel> getStocks(int hacienda);
}
