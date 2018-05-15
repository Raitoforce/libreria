package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.service.HistorialVentaService;
import com.work.backendlibrary.service.InventarioService;
import com.work.backendlibrary.service.StockService;
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

    @Autowired
    @Qualifier("stockService")
    StockService stockService;

    @Override
    public List<HistorialVenta> getPedidosPendientes(){
        List<HistorialVenta> cola=new ArrayList<HistorialVenta>();
        for (HistorialVenta hv: hvService.listAllHistorialVentas()){
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
        for(Stock stock: stockService.consultarByLibro(hv.getLibro().getClave_producto())){
        	if(stock.getCantidad()>=entregados){
        		stock.setCantidad(stock.getCantidad()-entregados);
        		break;
        	}else{
        		entregados=entregados-stock.getCantidad();
        		stock.setCantidad(0);
        	}
        }
        
    }

    @Override
    public void generarReporte(String folio){
    }
}
