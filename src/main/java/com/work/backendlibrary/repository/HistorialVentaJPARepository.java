package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Repository("historialVentaJPARepository")
public interface HistorialVentaJPARepository extends JpaRepository<HistorialVenta, Serializable> {

    List<HistorialVenta> findByVentaFolio(String id);

    List<HistorialVenta> findByVentaHacienda(int hacienda);

    List<HistorialVenta> findByVentaHaciendaAndVentaBloqueFolioFolioIdtemporadaIdtemporada(int hacienda, int temporada);

    HistorialVenta findByIdHistorial(int id);

    @Query("select max(hv.numresurtido) from HistorialVenta hv where venta.folio=?1")
    int getMAX(String folio);

    List<HistorialVenta> findByNumresurtidoAndVentaFolio(int num, String folio);

    @Query("select max(hv.fechaSolicitud) from HistorialVenta hv where venta.folio=?1 and hv.numresurtido= ?2")
    Date getDate(String folio, int num);

    @Query("select DISTINCT  hv.numresurtido from HistorialVenta hv where venta.folio=?1")
    List<Integer> getNumResurtidos(String folio);

    List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String clave, int idtemporada);

    List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idtemporada);

    List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaProfesorIdprofesorAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idprofesor, int idtemporada);

    @Modifying
    @Transactional
    @Query("delete from HistorialVenta hventa where venta.folio=?1 and hventa.numresurtido= ?2")
    void DeleteByNumresurtidoAndFolioVenta(String folio, int numresurtido);

    List<HistorialVenta> findByVentaBloqueFolioFolioIdtemporadaIdtemporadaAndVentaLideresLiderIdprofesor(int idtemporada, int idlider);
}
