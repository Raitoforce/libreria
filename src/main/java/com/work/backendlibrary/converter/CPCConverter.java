package com.work.backendlibrary.converter;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.model.CPCModel;
import com.work.backendlibrary.service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("cpcConverter")
public class CPCConverter{
    @Autowired
    @Qualifier("historialVentaService")
    HistorialVentaService historialVentaService;


    public CuentasPorCobrar model2entity(CPCModel cpcModel){
        CuentasPorCobrar cpc=new CuentasPorCobrar();
        cpc.setFecha(cpcModel.getFecha());
        cpc.setIdMovimiento(cpcModel.getId_movimiento());
        cpc.setPago(cpcModel.getPago());
        cpc.setHistorialVenta(historialVentaService.consultarHistorialVenta(cpcModel.getIdHistorial()));

        return  cpc;
    }
}
