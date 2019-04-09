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

    public abstract List<Venta> findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(String clave, int idtemporada, int hacienda);

    public abstract List<Venta> findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int iddirector, int idtemporada, int hacienda);

    public abstract List<Venta> findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int idprofesor, int idtemporada, int hacienda);

    public abstract List<Venta> findByBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int temporada, int hacienda);
}
 