package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.BloqueFolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository("bloqueFolioJPARepository")
public interface BloqueFoliosJPARepository extends JpaRepository<BloqueFolio, Serializable> {
    BloqueFolio findByVendedorClaveAndFolioIdfolios(String clave, int id);

    @Modifying
    @Transactional
    @Query("delete from BloqueFolio bfolio where vendedor_clave = ?1 and folio_idfolios = ?2")
    void deleteByClaveAndId(String clave, int id);

    @Query("select bf from BloqueFolio bf where inicio <= ?1 and fin >= ?1 and folio.idfolios=?2")
    BloqueFolio findByIsRange(int valor, int folio);

    BloqueFolio findByVendedorClaveAndFolioIdtemporadaIdtemporada(String clave, int idtemporada);


    @Query("select bf from BloqueFolio bf where inicio <= ?2 and fin >= ?2 and vendedor_clave=?1 and folio.tipo='VENTA'")
    BloqueFolio findByVendedorAndRange(String clave, int valor);

    @Query("select bf from BloqueFolio bf where inicio <= ?2 and fin >= ?2 and vendedor_clave=?1 and folio.tipo=?3")
    BloqueFolio findByVendedorAndRangeType(String clave, int valor, String type);
    
    List<BloqueFolio> findByFolioIdtemporadaIdtemporada(int id);
    
}
