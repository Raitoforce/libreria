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

    public abstract List<HistorialVenta> findByVentaFolio(String id);

    public abstract List<HistorialVenta> findByVentaHacienda(int hacienda);

    public abstract List<HistorialVenta> findByVentaHaciendaAndVentaBloqueFolioFolioIdtemporadaIdtemporada(int hacienda, int temporada);

    public abstract HistorialVenta findByIdHistorial(int id);

    @Query("select max(hv.numresurtido) from HistorialVenta hv where venta.folio=?1")
    public abstract int getMAX(String folio);

    public abstract List<HistorialVenta> findByNumresurtidoAndVentaFolio(int num, String folio);

    @Query("select max(hv.fechaSolicitud) from HistorialVenta hv where venta.folio=?1 and hv.numresurtido= ?2")
    public abstract Date getDate(String folio, int num);

    @Query("select DISTINCT  hv.numresurtido from HistorialVenta hv where venta.folio=?1")
    public abstract List<Integer> getNumResurtidos(String folio);

    public abstract List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String clave, int idtemporada);

    public abstract List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idtemporada);

    public abstract List<HistorialVenta> findByVentaBloqueFolioVendedorClaveAndVentaEscuelaClaveAndVentaProfesorIdprofesorAndVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idprofesor, int idtemporada);

    @Modifying
    @Transactional
    @Query("delete from HistorialVenta hventa where venta.folio=?1 and hventa.numresurtido= ?2")
    public abstract void DeleteByNumresurtidoAndFolioVenta(String folio, int numresurtido);

    public abstract List<HistorialVenta> findByVentaBloqueFolioFolioIdtemporadaIdtemporadaAndVentaLideresLiderIdprofesor(int idtemporada, int idlider);
}
