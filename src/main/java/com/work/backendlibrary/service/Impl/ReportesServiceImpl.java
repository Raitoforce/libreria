package com.work.backendlibrary.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.converter.InventarioConverterRConverter;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.model.VentaReportModel;
import com.work.backendlibrary.repository.EscuelaJPARepository;
import com.work.backendlibrary.repository.QueryDSLRepository;
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
	
	@Autowired
	@Qualifier("inventarioConverterRConverter")
	InventarioConverterRConverter icrc;
	
	@Autowired
	@Qualifier("queryDSLRepository")
	QueryDSLRepository qDSLR;
	
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
	public void generarReporteZonas(String vendedor) {
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
    		
    	    JRBeanCollectionDataSource sourceVendedor = null; 
    	    
    	    if(vendedor.compareTo("")==0){
    	    	sourceVendedor = new JRBeanCollectionDataSource(vJPA.findAll());
    	    }else{
    	    	ArrayList<Vendedor> vendedores = new ArrayList<>();
    	    	vendedores.add(vJPA.findByClave(vendedor));
    	    	sourceVendedor = new JRBeanCollectionDataSource(vendedores);
    	    }
             ///Compile the master and sub report 
            
            JasperCompileManager.compileReportToFile(subsubReportFileName,path+"REscuelas.jasper");
            JasperReport jasperSubReport2= JasperCompileManager.compileReport(subReportFileName);
    	    
            JasperCompileManager.compileReportToFile(subReportFileName,path+"SubZonas.jasper");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subsubReportFileName);
            
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
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

	@Override
	public void generarReporteInventario() {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("RZona.jrxml","");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	masterReportFileName=path+"ReporteInventario.jrxml";
	    	subReportFileName=path+"StocksR.jrxml";
	    	jasperMaster=path+"ReporteInventario.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		
    	    JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(this.icrc.converterInventarioRModel());
    	    
             ///Compile the master and sub report 
    	    
            JasperCompileManager.compileReportToFile(subReportFileName,path+"StocksR.jasper");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportFileName);
            
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            //Map parameters = new HashMap();
            //parameters.put("subreportParameter", jasperSubReport);
            //parameters.put("subreportParameter2",jasperSubReport2);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,null,source);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
	}

	@Override
	public void generarReporteVentas(String vendedor, String libro, Date fechaInicial, Date fechaFinal,
			int tipoPedido) {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("RZona.jrxml","");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	masterReportFileName=path+"ReporteVentas.jrxml";
	    	subReportFileName=path+"ReportePedidos.jrxml";
	    	jasperMaster=path+"ReporteVentas.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		
    	    JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(this.qDSLR.findVentaByVendedorLibroFecha(vendedor, libro, fechaInicial, fechaFinal, tipoPedido));
    	    
             ///Compile the master and sub report 
    	    
            JasperCompileManager.compileReportToFile(subReportFileName,path+"ReportePedidos.jasper");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportFileName);
            
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,null,source);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
	}
	
	@Override
	public void generarReporteCobranza(String clave,String escuela,int profesor) {
		if(path==null){
	    	try {
	    		path= reporte_model.getURL().getPath().replaceAll("%20"," ");
				path=path.replaceAll("RZona.jrxml","");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	masterReportFileName=path+"ReporteCobranza.jrxml";
	    	subReportFileName=path+"ReporteEscuelas.jrxml";
	    	subsubReportFileName=path+"ReporteProfesores.jrxml";
	    	jasperMaster=path+"ReporteCobranza.jasper";
	    	destFile=path+"reporte.pdf";
    	}
    	try {
    		
    	    JRBeanCollectionDataSource source = null;
    	    source = new JRBeanCollectionDataSource(this.qDSLR.findVendedorByClaveEscuelaProfesor(clave, escuela, profesor)); 
             ///Compile the master and sub report 
            
            JasperCompileManager.compileReportToFile(subsubReportFileName,path+"ReporteProfesores.jasper");
            JasperReport jasperSubReport2= JasperCompileManager.compileReport(subReportFileName);
    	    
            JasperCompileManager.compileReportToFile(subReportFileName,path+"ReporteEscuelas.jasper");
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subsubReportFileName);
            
            JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
            
            System.out.println("Llenando...");
            reporte=JasperFillManager.fillReportToFile(jasperMaster,null,source);
            if(reporte!=null){
            	JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
         } catch (JRException e) {

            e.printStackTrace();
         }
         System.out.println("Done filling!!! ...");
	}

}
