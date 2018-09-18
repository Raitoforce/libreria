package com.work.backendlibrary.service;

import java.util.Date;

public interface ReportesService {
	public abstract void generarReporteZonas(String vendedor);
	public abstract void generarReporteInventario();
	public abstract void generarReporteVentas(String vendedor,String libro,Date fechaInicial,Date fechaFinal,int tipoPedido);
	void generarReporteCobranza(String clave,String escuela,int profesor,Date fechaInicial, Date fechaFinal);
	public abstract void generarReporteComisiones(int tipo,String id,int temporada);
	public abstract void generarReporteGanancia(String vendedor,String libro,Date fechaInicial,Date fechaFinal,int tipoPedido);
}
