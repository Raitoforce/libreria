package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.converter.HistorialVentaConverter;
import com.work.backendlibrary.entity.HistorialVenta;
import com.work.backendlibrary.model.HistorialVentaModel;
import com.work.backendlibrary.repository.HistorialVentaJPARepository;
import com.work.backendlibrary.service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("historialVentaService")
public class HistorialVentaServiceImpl implements HistorialVentaService {
    @Autowired
    @Qualifier("historialVentaJPARepository")
    HistorialVentaJPARepository hvJPA;
    @Autowired
    @Qualifier("historialVentaConverter")
    HistorialVentaConverter hvConverter;


    @Override
    public List<HistorialVenta> listAllHistorialVentas() {
        // TODO Auto-generated method stub
        return hvJPA.findAll();
    }

    @Override
    public List<HistorialVenta> listAllHistorialVentasByHacienda(int hacienda, int temporada) {
//		return hvJPA.findByVentaHacienda(hacienda);
        return hvJPA.findByVentaHaciendaAndVentaBloqueFolioFolioIdtemporadaIdtemporada(hacienda, temporada);
    }

    @Override
    public HistorialVenta addHistorialVenta(HistorialVentaModel hventam) {
        // TODO Auto-generated method stub
        HistorialVenta hventa = hvConverter.model2entity(hventam);
        return hvJPA.save(hventa);
    }

    @Override
    public void removeHistorialVenta(int id) {
        // TODO Auto-generated method stub
        hvJPA.deleteById(id);
    }

    @Override
    public HistorialVenta updateHistorialVenta(HistorialVentaModel hventam) {
        // TODO Auto-generated method stub
        HistorialVenta hventa = hvConverter.model2entity(hventam);
        return hvJPA.save(hventa);
    }

    @Override
    public HistorialVenta consultarHistorialVenta(int id) {
        // TODO Auto-generated method stub
        return hvJPA.findByIdHistorial(id);
    }

    @Override
    public HistorialVenta updateInventario(HistorialVenta historialVenta) {
        return hvJPA.save(historialVenta);
    }

    @Override
    public List<HistorialVenta> consultarByVenta(String idVenta) {
        // TODO Auto-generated method stub
        return hvJPA.findByVentaFolio(idVenta);
    }

    @Override
    public Integer getMaximo(String folio) {
        // TODO Auto-generated method stub
        return hvJPA.getMAX(folio);
    }

    @Override
    public List<HistorialVenta> consultarByNumResurtido(int numresurtido, String folio) {
        // TODO Auto-generated method stub
        return hvJPA.findByNumresurtidoAndVentaFolio(numresurtido, folio);
    }

    @Override
    public void eliminarByResurtido(String folio, int numresurtido) {
        hvJPA.DeleteByNumresurtidoAndFolioVenta(folio, numresurtido);
    }

    @Override
    public boolean numresurtidoHasConfirmed(String folio, int numresurtido) {
        // TODO Auto-generated method stub
        List<HistorialVenta> pedidos = hvJPA.findByNumresurtidoAndVentaFolio(numresurtido, folio);
        for (HistorialVenta pedido : pedidos) {
            if (pedido.getFechaConfirmacion() != null)
                return true;
        }
        return false;
    }
}
