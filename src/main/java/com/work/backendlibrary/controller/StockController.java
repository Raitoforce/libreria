package com.work.backendlibrary.controller;

import com.work.backendlibrary.entity.Stock;
import com.work.backendlibrary.model.StockModel;
import com.work.backendlibrary.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    @Qualifier("stockService")
    StockService stockService;


    @GetMapping("")
    public ResponseEntity<List<Stock>> devolverStocks(){
        List<Stock> stocks=stockService.listAllStock();
        return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> consultarStock(@PathVariable("id") int id){
        Stock stock=stockService.consultarStock(id);
        return new ResponseEntity<Stock>(stock,HttpStatus.OK);

    }
    
    @GetMapping("/libro={libro}")
    public ResponseEntity<List<Stock>> consultarStock(@PathVariable("libro")String libro){
    	List<Stock> stocks=stockService.consultarByLibro(libro);
        return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);

    }

    @PostMapping(path="/nuevo",consumes="application/json")
    public ResponseEntity<String> insertarStock(@RequestBody StockModel stockModel){
        stockService.addStock(stockModel);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping(path="",consumes="application/json")
    public ResponseEntity<String> actualizarStock(@RequestBody StockModel stockModel){
        stockService.updateStock(stockModel);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> eliminarStock(@PathVariable("id")int id){
        stockService.removeStock(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/hacienda={hacienda}")
    public ResponseEntity<List<Stock>> consultarByHacienda(@PathVariable("hacienda")int hacienda){
        List<Stock> stocks=stockService.getByHacienda(hacienda);
        return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);

    }
}
