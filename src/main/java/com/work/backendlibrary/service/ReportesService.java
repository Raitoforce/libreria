package com.work.backendlibrary.service;

import java.sql.Date;

public interface ReportesService {
    void generarReporteZonas(String vendedor);

    void generarReporteInventario();

    void generarReporteVentas(String vendedor, String libro, Date fechaInicial, Date fechaFinal, int tipoPedido, int temporada, int hacienda);

    void generarReporteCobranza(String clave, String escuela, int profesor, Date fechaInicial, Date fechaFinal, int temporada);

    void generarReporteComisiones(int tipo, String id, int temporada, int hacienda);

    void generarReporteGanancia(String vendedor, String libro, Date fechaInicial, Date fechaFinal, int tipoPedido, int temporada);
}
