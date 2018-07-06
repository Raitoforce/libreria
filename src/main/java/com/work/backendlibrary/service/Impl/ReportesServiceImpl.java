package com.work.backendlibrary.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.VentaReportModel;
import com.work.backendlibrary.service.ReportesService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("reportesService")
public class ReportesServiceImpl implements ReportesService{
	
	@Value("classpath:/RZona.jrxml")
	private Resource reporte_model;
	
	String path=null;
	String masterReportFileName =null;
	String subReportFileName =null;
	String subEscuela=null;
	String destFile=null;
	String jasperMaster=null;
	
	@Override
	public void generarReporteZonas() {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("RZona.jrxml","");
			} catch (IOException e) {
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

}
