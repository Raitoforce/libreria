package com.work.backendlibrary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.converter.ResurtidosVentaConverter;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.LibroStockModel;
import com.work.backendlibrary.model.ResurtidosModel;
import com.work.backendlibrary.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController{
	@Autowired
	@Qualifier("inventarioService")
	InventarioService inventarioService;
	
	@Autowired
	@Qualifier("resurtidosVentaConverter")
	ResurtidosVentaConverter rsc;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("classpath:/Invoice.jrxml")
	private Resource reporte;

	
	@GetMapping("")
	public ResponseEntity<List<HistorialVenta>> devolverInventario(){
		List<HistorialVenta> inventario=inventarioService.getPedidosPendientes();
		return new ResponseEntity<>(inventario,HttpStatus.OK);
	}
	
	
	@GetMapping("/resurtidos={folio}")
	public ResponseEntity<List<ResurtidosModel>> devolverResurtidos(@PathVariable("folio")String folio){
		List<ResurtidosModel> resurtidos= rsc.convertir(folio);
		return new ResponseEntity<>(resurtidos,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/stocks")
	public ResponseEntity<List<LibroStockModel>> devolverStocks(){
		List<LibroStockModel> inventario=inventarioService.getStocks();
		return new ResponseEntity<>(inventario,HttpStatus.OK);
	}
	
	@GetMapping("/confirmar")
	public ResponseEntity<String> confirmarPedido(@RequestParam("idHistorial")int idHistorial,@RequestParam("entregados")int entregados){
		inventarioService.confirmarPedido(idHistorial, entregados);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/stockActual={clave_libro}")
	public ResponseEntity<Integer> getStockByLibro(@PathVariable("clave_libro") String claveLibro){
		return new ResponseEntity<>(inventarioService.getStockActualTotal(claveLibro),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> crearPDF(@RequestParam("folio")String folio){
		inventarioService.generarReporte(folio);
		
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			//path=resourceLoader.getResource("Invoice.jrxml").getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	@GetMapping("/pdfPedido")
	public ResponseEntity<byte[]> crearPDFPedido(@RequestParam("folio")String folio,@RequestParam("idHistorial")int idHistorial){
		inventarioService.generarReportePedido(folio, idHistorial);
		String path="";
		try {
			path= reporte.getURL().getPath().replaceAll("%20"," ");
			//path=resourceLoader.getResource("Invoice.jrxml").getURL().getPath().replaceAll("%20"," ");
			path=path.replaceAll("Invoice.jrxml","")+"reporte.pdf";
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
