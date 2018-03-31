package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.VendedorModel;

import java.util.List;

public interface VendedorService {
    Vendedor addVendedor(Vendedor vendedor);

    Boolean findByClave(String clave);

    Boolean findByRfc(String rfc);

    List<Vendedor> listAll();
    
    List<VendedorModel> listAllModel();

    boolean findByEmail(String email);
}
