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
import org.springframework.web.bind.annotation.RestController;

import com.work.backendlibrary.entity.HaciendaStock;
import com.work.backendlibrary.model.StockHaciendaModel;
import com.work.backendlibrary.service.HaciendaStockService;

@RestController
@RequestMapping("/hstock")
public class HaciendaStockController {
	@Autowired
    @Qualifier("haciendaStockService")
    HaciendaStockService stockService;


    @GetMapping("")
    public ResponseEntity<List<HaciendaStock>> devolverStocks(){
        List<HaciendaStock> stocks=stockService.listAllStock();
        return new ResponseEntity<List<HaciendaStock>>(stocks,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HaciendaStock> consultarStock(@PathVariable("id") int id){
    	HaciendaStock stock=stockService.consultarStock(id);
        return new ResponseEntity<HaciendaStock>(stock,HttpStatus.OK);

    }
    
    @GetMapping("/libro={libro}")
    public ResponseEntity<List<HaciendaStock>> consultarStock(@PathVariable("libro")String libro){
    	List<HaciendaStock> stocks=stockService.consultarByLibro(libro);
        return new ResponseEntity<List<HaciendaStock>>(stocks,HttpStatus.OK);

    }

    @PostMapping(path="/nuevo",consumes="application/json")
    public ResponseEntity<String> insertarStock(@RequestBody StockHaciendaModel stockModel){
        stockService.addStock(stockModel);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping(path="",consumes="application/json")
    public ResponseEntity<String> actualizarStock(@RequestBody StockHaciendaModel stockModel){
        stockService.updateStock(stockModel);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> eliminarStock(@PathVariable("id")int id){
        stockService.removeStock(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
