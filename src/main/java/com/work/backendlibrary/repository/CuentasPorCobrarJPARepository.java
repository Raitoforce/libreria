package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("cuentasPorCobrarJPARepository")
public interface CuentasPorCobrarJPARepository extends JpaRepository<CuentasPorCobrar, Serializable> {
    public abstract CuentasPorCobrar findByIdMovimiento(int id);

    public abstract List<CuentasPorCobrar> findByHistorialVentaIdHistorial(int idHistorial);

    public abstract List<CuentasPorCobrar> findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(String clave, int idtemporada);

    public abstract List<CuentasPorCobrar> findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaEscuelaClaveAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idtemporada);

    public abstract List<CuentasPorCobrar> findByHistorialVentaVentaBloqueFolioVendedorClaveAndHistorialVentaVentaEscuelaClaveAndHistorialVentaVentaProfesorIdprofesorAndHistorialVentaVentaBloqueFolioFolioIdtemporadaIdtemporada(String claveV, String claveE, int idprofesor, int idtemporada);
}