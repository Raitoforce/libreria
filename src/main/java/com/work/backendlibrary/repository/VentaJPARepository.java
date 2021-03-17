package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("ventaJPARepository")
public interface VentaJPARepository extends JpaRepository<Venta, Serializable> {

    Venta findByFolio(String folio);

    Venta findByFolioAndBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporada(String folio, String clave, int idtemporada);

    List<Venta> findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(String clave, int idtemporada, int hacienda);

    List<Venta> findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int iddirector, int idtemporada, int hacienda);

    List<Venta> findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int idprofesor, int idtemporada, int hacienda);

    List<Venta> findByBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(int temporada, int hacienda);
}
 