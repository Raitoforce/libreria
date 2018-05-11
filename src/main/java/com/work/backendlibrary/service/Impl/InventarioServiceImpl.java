package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.service.HistorialVentaService;
import com.work.backendlibrary.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("inventarioService")
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    @Qualifier("historialVentaService")
    HistorialVentaService hvService;

    @Override
    public List<HistorialVenta> getPedidosPendientes(){
        List<HistorialVenta> cola=new ArrayList<HistorialVenta>();
        for (HistorialVenta hv: hvService.listAllHistorialVentas()) {
            if(hv.getEntregados()-hv.getPedidos()!=0)
                cola.add(hv);
        }
        return cola;
    }

    @Override
    public void confirmarPedido(int idHistorial, int entregados){
        HistorialVenta hv=hvService.consultarHistorialVenta(idHistorial);
        hv.setEntregados(hv.getEntregados()+entregados);
        hv.setFechaConfirmacion(new Timestamp(System.currentTimeMillis()));
        hvService.updateInventario(hv);
    }

    @Override
    public void generarReporte(String folio) {

    }
}
