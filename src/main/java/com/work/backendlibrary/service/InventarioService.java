package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.HistorialVenta;

import java.util.List;

public interface InventarioService{
    public abstract List<HistorialVenta> getPedidosPendientes();

    public abstract void confirmarPedido(int idHistorial,int entregados);

    public abstract void generarReporte(String folio);
    
    public abstract int getStockActualTotal(String clave);
     
}
