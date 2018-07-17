package com.work.backendlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.service.ReportesService;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
	@Autowired
	@Qualifier("reportesService")
	ReportesService rService;
	
	@GetMapping("/pdfZona")
	public ResponseEntity<String> crearPDFZona(){
		rService.generarReporteZonas();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}