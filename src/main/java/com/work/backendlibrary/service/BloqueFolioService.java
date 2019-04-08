package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.BloqueFolio;

import java.util.List;

public interface BloqueFolioService {
    public abstract List<BloqueFolio> listAllBloqueFolios();

    public abstract BloqueFolio addBloqueFolio(BloqueFolio bf);

    public abstract void removeBloqueFolio(String clave, int id);

    public abstract BloqueFolio updateFolio(BloqueFolio bf);

    public abstract BloqueFolio consultarFolio(String clave, int id);

    public abstract boolean isInRange(int valor, int idfolio);

    public abstract BloqueFolio consultarByVendedorAndTemporada(String clave, int idtemporada);

    public abstract List<BloqueFolio> consultarByTemporada(int idtemporada);

    public abstract BloqueFolio isInRangeAndVendedor(String clave, int valor);

    public abstract boolean isAValidFolio(String clave, int valor);

    public abstract boolean isAValidFolioType(String clave, int valor, String type);
}
