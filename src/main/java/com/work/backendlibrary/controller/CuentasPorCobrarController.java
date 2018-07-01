package com.work.backendlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.model.CuentasProfesorModel;
import com.work.backendlibrary.model.CuentasVEModel;
import com.work.backendlibrary.service.CuentasPorCobrarService;

@RestController
@RequestMapping("/cuentas")
public class CuentasPorCobrarController {
	@Autowired
	@Qualifier("cuentasPorCobrarService")
	CuentasPorCobrarService cpcService;
	
	@GetMapping("")
	public ResponseEntity<List<CuentasPorCobrar>> devolverCuentasPorCobrar(){
		List<CuentasPorCobrar> cuentas = cpcService.listAllCuentas();
		return new ResponseEntity<List<CuentasPorCobrar>>(cuentas,HttpStatus.OK);
	}
	
	@GetMapping("/abonar")
	public ResponseEntity<String> abanonarCuentas(@RequestParam("monto")int monto,@RequestParam("claveV")String claveV,@RequestParam("claveE")String claveE,@RequestParam("idprofesor")int idprofesor,@RequestParam("idtemporada")int idtemporada){
		cpcService.insertarMonto(monto, claveV, claveE, idprofesor,idtemporada);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/cuentasByProfesor")
	public ResponseEntity<List<CuentasProfesorModel>> cuentaProfesor(@RequestParam("claveV")String claveV,@RequestParam("claveE")String claveE,@RequestParam("idtemporada")int idtemporada){
		return new ResponseEntity<>(cpcService.consultaCuentaByProfesor(claveE, claveV, idtemporada),HttpStatus.OK);
	}
	
	@GetMapping("/cuentasByEscuela")
	public ResponseEntity<List<CuentasVEModel>> cuentaEscuela(@RequestParam("claveV")String claveV,@RequestParam("idtemporada")int idtemporada){
		
		return new ResponseEntity<>(cpcService.consultaCuentaByEscuela(claveV, idtemporada),HttpStatus.OK);
	}
	
	@GetMapping("/cuentasByVendedor")
	public ResponseEntity<List<CuentasVEModel>> cuentaVendedor(@RequestParam("idtemporada")int idtemporada){
		return new ResponseEntity<>(cpcService.consultaCuentaByVendedor(idtemporada),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CuentasPorCobrar> consultaCuentaPorCobrar(@PathVariable("id") int id){
		CuentasPorCobrar cuenta= cpcService.consultarCuenta(id);
		return new ResponseEntity<CuentasPorCobrar>(cuenta,HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevo")
	public ResponseEntity<String> insertarCuentaPorCobrar(@RequestBody CuentasPorCobrar cuenta){
		cpcService.addCuenta(cuenta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> eliminarCuentaPorCobrar(@PathVariable("id") int id){
		cpcService.removeCuenta(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(path="",consumes="application/json")
	public ResponseEntity<String> actualizarCuentaPorCobrar(@RequestBody CuentasPorCobrar cuenta){
		cpcService.updateCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
}
