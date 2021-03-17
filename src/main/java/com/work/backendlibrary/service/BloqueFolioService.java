package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.BloqueFolio;

import java.util.List;

public interface BloqueFolioService {
    List<BloqueFolio> listAllBloqueFolios();

    BloqueFolio addBloqueFolio(BloqueFolio bf);

    void removeBloqueFolio(String clave, int id);

    BloqueFolio updateFolio(BloqueFolio bf);

    BloqueFolio consultarFolio(String clave, int id);

    boolean isInRange(int valor, int idfolio);

    BloqueFolio consultarByVendedorAndTemporada(String clave, int idtemporada);

    List<BloqueFolio> consultarByTemporada(int idtemporada);

    BloqueFolio isInRangeAndVendedor(String clave, int valor);

    boolean isAValidFolio(String clave, int valor);

    boolean isAValidFolioType(String clave, int valor, String type);
}
