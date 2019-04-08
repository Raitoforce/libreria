package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("ventaJPARepository")
public interface VentaJPARepository extends JpaRepository<Venta, Serializable> {

    public abstract Venta findByFolio(String folio);

    public abstract Venta findByFolioAndBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporada(String folio, String clave, int idtemporada);

    public abstract List<Venta> findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporada(String clave, int idtemporada);

    public abstract List<Venta> findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporada(int iddirector, int idtemporada);

    public abstract List<Venta> findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporada(int idprofesor, int idtemporada);

    public abstract List<Venta> findByBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int temporada, int hacienda);
}
 