package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.converter.LibroStockConverter;
import com.work.backendlibrary.converter.VentaReportConverter;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.LibroStockModel;
import com.work.backendlibrary.model.VentaReportModel;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("inventarioService")
public class InventarioServiceImpl implements InventarioService{
	
	//@Autowired
	//ResourceLoader resourceLoader;
	String path=null;
	String masterReportFileName =null;
	String subReportFileName =null;
	String reporte=null;
	String destFile=null;
	String jasperMaster;
	
	@Autowired
	@Qualifier("libroStockConverter")
	LibroStockConverter lsc;
	
    @Autowired
    @Qualifier("historialVentaService")
    HistorialVentaService hvService;
    
    @Autowired
    @Qualifier("ventaService")
    VentaService ventaService;

    @Autowired
    @Qualifier("stockService")
    StockService stockService;
    
    @Autowired
    @Qualifier("ventaReportConverter")
    VentaReportConverter ventaReportC;
    
    @Value("classpath:/Invoice.jrxml")
	private Resource reporte_model;
    
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
        for(Stock stock: stockService.consultarByLibro(hv.getLibro().getClave_producto())){
        	if(entregados>0 && stock.getCantidad()>0){
	        	if(stock.getStock_actual()>=entregados){
	        		stock.setStock_actual(stock.getStock_actual()-entregados);
	        		break;
	        	}else{
	        		entregados=entregados-stock.getStock_actual();
	        		stock.setStock_actual(0);
	        	}
	        }else{
	        		if(stock.getCantidad()>Math.abs(entregados)+stock.getStock_actual()){
	        			stock.setStock_actual(stock.getStock_actual()-entregados);
	        			break;
	        		}else{
	        			entregados=entregados+stock.getCantidad()-stock.getStock_actual();
	        			stock.setStock_actual(stock.getCantidad());
	        		}
	        }
        	stockService.updtateInventario(stock);
        }
        hvService.updateInventario(hv);        
    }

    @Override
    public void generarReporte(String folio){
    	if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				//path=resourceLoader.getResource("Invoice.jrxml").getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("Invoice.jrxml","");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	masterReportFileName=path+"Invoice.jrxml";
	    	subReportFileName=path+"pedidos.jrxml";
	    	jasperMaster=path+"Invoice.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		Venta v=ventaService.consultarVenta(folio);
    		VentaReportModel venta=ventaReportC.entity2model(v);
    		venta.setPedidos(ventaReportC.entity2modelPedidos(hvService.consultarByVenta(folio)));
    		venta.Calcular(v.getComisionProfesor());
    		ArrayList<VentaReportModel> ventas=new ArrayList<>();
    		ventas.add(venta);
    	    JRBeanCollectionDataSource sourceVenta = new JRBeanCollectionDataSource(ventas);
    	    //JRBeanCollectionDataSource sourcePedidos=new JRBeanCollectionDataSource(venta.getPedidos());
    		
            /* Compile the master and sub report */
            //JasperReport jasperMasterReport = JasperCompileManager
               //.compileReport(masterReportFileName);
    	    //JasperCompileManager.compileReportToFile(subReportFileName,path+"pedidos.jasper");
            JasperReport jasperSubReport = JasperCompileManager
               .compileReport(subReportFileName);
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            Map parameters = new HashMap();
            parameters.put("subreportParameter", jasperSubReport);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,parameters,sourceVenta);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
     }

	@Override
	public int getStockActualTotal(String clave) {
		// TODO Auto-generated method stub
		int cont=0;
		for(Stock stock: stockService.consultarByLibro(clave)){
			cont+=stock.getStock_actual();
		}
		return cont;
	}

	@Override
	public List<LibroStockModel> getStocks() {
		// TODO Auto-generated method stub
		return lsc.convertir();
	}

	@Override
	public void generarReportePedido(String folio,int numresurtido) {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				//path=resourceLoader.getResource("Invoice.jrxml").getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("Invoice.jrxml","");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	masterReportFileName=path+"Invoice.jrxml";
	    	subReportFileName=path+"pedidos.jrxml";
	    	jasperMaster=path+"Invoice.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		Venta v=ventaService.consultarVenta(folio);
    		VentaReportModel venta=ventaReportC.entity2modelp(v,numresurtido);
    		venta.setPedidos(ventaReportC.entity2modelPedidos(hvService.consultarByNumResurtido(numresurtido,folio)));
    		venta.Calcular(v.getComisionProfesor());
    		ArrayList<VentaReportModel> ventas=new ArrayList<>();
    		ventas.add(venta);
    	    JRBeanCollectionDataSource sourceVenta = new JRBeanCollectionDataSource(ventas);
    	    //JRBeanCollectionDataSource sourcePedidos=new JRBeanCollectionDataSource(venta.getPedidos());
    		
            /* Compile the master and sub report */
            //JasperReport jasperMasterReport = JasperCompileManager
               //.compileReport(masterReportFileName);
    	    //JasperCompileManager.compileReportToFile(subReportFileName,path+"pedidos.jasper");
            JasperReport jasperSubReport = JasperCompileManager
               .compileReport(subReportFileName);
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            Map parameters = new HashMap();
            parameters.put("subreportParameter", jasperSubReport);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,parameters,sourceVenta);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
	}

}
