package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.service.HistorialVentaService;
import com.work.backendlibrary.service.InventarioService;
import com.work.backendlibrary.service.StockService;
import com.work.backendlibrary.service.VentaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("inventarioService")
public class InventarioServiceImpl implements InventarioService {
	
	
	String masterReportFileName = "/Invoice.jrxml";
	String subReportFileName = "/pedidos.jrxml";
	String destFile="reporte.pdf";
	String reporte=null;
	
    @Autowired
    @Qualifier("historialVentaService")
    HistorialVentaService hvService;
    
    @Autowired
    @Qualifier("ventaService")
    VentaService ventaService;

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
    	String reporte;
    	try {
    		Venta venta=ventaService.consultarVenta(folio);
    	    JRBeanCollectionDataSource sourceVenta = new JRBeanCollectionDataSource(null, new ArrayList<>().add(venta));
    		
            /* Compile the master and sub report */
            JasperReport jasperMasterReport = JasperCompileManager
               .compileReport(masterReportFileName);
            JasperReport jasperSubReport = JasperCompileManager
               .compileReport(subReportFileName);
            Map parameters = new HashMap();
            
            reporte=JasperFillManager.fillReportToFile(masterReportFileName,parameters,sourceVenta);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
     }
}
