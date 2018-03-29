package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.Vendedor;

import java.util.List;

public interface VendedorService {
    Vendedor addVendedor(Vendedor vendedor);

    Boolean findByClave(String clave);

    Boolean findByRfc(String rfc);

    List<Vendedor> listAll();

    boolean findByEmail(String email);
}
