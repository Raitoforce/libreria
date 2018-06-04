package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.LibroStockModel;

import java.util.List;

public interface InventarioService{
    public abstract List<HistorialVenta> getPedidosPendientes();

    public abstract void confirmarPedido(int idHistorial,int entregados);

    public abstract void generarReporte(String folio);
    
    public abstract void generarReportePedido(String folio, int idHistorial);
    
    public abstract int getStockActualTotal(String clave);
    
    public abstract List<LibroStockModel> getStocks();
}
