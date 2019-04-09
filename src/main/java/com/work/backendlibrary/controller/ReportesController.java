package com.work.backendlibrary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.service.ReportesService;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
	@Autowired
	@Qualifier("reportesService")
	ReportesService rService;
	
	@Value("classpath:/Invoice.jrxml")
	private Resource reporte;
	
	@GetMapping("/pdfZona")
	public ResponseEntity<byte[]> crearPDFZona(@RequestParam(name="vendedor",defaultValue="")String vendedor){
		rService.generarReporteZonas(vendedor);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/inventario")
	public ResponseEntity<byte[]> crearPDFInventario(){
		rService.generarReporteInventario();
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Venta")
	public ResponseEntity<byte[]> crearPDFVenta(@RequestParam(name="vendedor",defaultValue="")String vendedor,
			@RequestParam(name="libro",defaultValue="")String libro,
			@RequestParam(name="fechaInicial",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicial,
			@RequestParam(name="fechaFinal",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFinal,
			@RequestParam(name="tipoPedido",defaultValue="0")int tipoPedido,
			@RequestParam(name="temporada", required = true)int temporada,
			@RequestParam(name="hacienda",defaultValue="0")int hacienda
			){
		rService.generarReporteVentas(vendedor, libro, fechaInicial, fechaFinal, tipoPedido,temporada, hacienda);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Cobranza")
	public ResponseEntity<byte[]> crearPDFCrobranza(@RequestParam(name="vendedor",defaultValue="")String vendedor,
			@RequestParam(name="escuela",defaultValue="")String escuela,
			@RequestParam(name="fechaInicial",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicial,
			@RequestParam(name="fechaFinal",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFinal,
			@RequestParam(name="profesor",defaultValue="0")int profesor
			){
		rService.generarReporteCobranza(vendedor, escuela, profesor,fechaInicial, fechaFinal);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Comisiones")
	public ResponseEntity<byte[]> crearPDFComisiones(@RequestParam(name="tipo")int tipo,
			@RequestParam(name="temporada")int temporada,
			@RequestParam(name="id",defaultValue="")String id){
		rService.generarReporteComisiones(tipo, id, temporada);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Ganancia")
	public ResponseEntity<byte[]> crearPDFGanancia(@RequestParam(name="vendedor",defaultValue="")String vendedor,
			@RequestParam(name="libro",defaultValue="")String libro,
			@RequestParam(name="fechaInicial",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicial,
			@RequestParam(name="fechaFinal",required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFinal,
			@RequestParam(name="tipoPedido",defaultValue="0")int tipoPedido
			){
		rService.generarReporteGanancia(vendedor, libro, fechaInicial, fechaFinal, tipoPedido);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e){
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename =path+"reporte.pdf";
	    byte[] data=null;
	    try {
	    	File file = new File(path);
	        data = new byte[(int) file.length()];
	        InputStream is = new FileInputStream(file);
	        is.read(data);
	        is.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
	    headers.setContentDispositionFormData(path,path);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(data,HttpStatus.ACCEPTED);
	}
}