package com.work.backendlibrary.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.VentaReportModel;
import com.work.backendlibrary.repository.EscuelaJPARepository;
import com.work.backendlibrary.repository.VendedorRepository;
import com.work.backendlibrary.repository.ZonaJPARepository;
import com.work.backendlibrary.service.ReportesService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("reportesService")
public class ReportesServiceImpl implements ReportesService{
	
	@Autowired
	@Qualifier("escuelaJPARepository")
	EscuelaJPARepository eJPA;
	
	@Autowired
	@Qualifier("vendedorRepository")
	VendedorRepository vJPA;
	
	@Autowired
	@Qualifier("zonaJPARepository")
	ZonaJPARepository zJPA;
	
	@Value("classpath:/RZona.jrxml")
	private Resource reporte_model;
	
	String path=null;
	String masterReportFileName =null;
	String subReportFileName =null;
	String subEscuela=null;
	String destFile=null;
	String jasperMaster=null;
	String subsubReportFileName=null;
	String reporte;
	
	@Override
	public void generarReporteZonas() {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("RZona.jrxml","");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	masterReportFileName=path+"RZona.jrxml";
	    	subReportFileName=path+"SubZonas.jrxml";
	    	subsubReportFileName=path+"REscuelas.jrxml";
	    	jasperMaster=path+"RZona.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		
    	    JRBeanCollectionDataSource sourceVendedor = new JRBeanCollectionDataSource(vJPA.findAll());
    	    
             ///Compile the master and sub report 
            
            JasperCompileManager.compileReportToFile(subsubReportFileName,path+"REscuela.jasper");
            JasperReport jasperSubReport2= JasperCompileManager.compileReport(subReportFileName);
    	    
            JasperCompileManager.compileReportToFile(subReportFileName,path+"SubZonas.jasper");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subsubReportFileName);
            
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            //Map parameters = new HashMap();
            //parameters.put("subreportParameter", jasperSubReport);
            //parameters.put("subreportParameter2",jasperSubReport2);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,null,sourceVendedor);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
	}

}
