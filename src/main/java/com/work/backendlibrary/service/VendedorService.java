package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.VendedorModel;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface VendedorService {
    Vendedor addVendedor(Vendedor vendedor);

    Boolean findByClave(String clave);

    Boolean findByRfc(String rfc);

    List<Vendedor> listAll();
    
    List<VendedorModel> listAllModel();
    
    List<VendedorModel> listPage(Pageable pageable);

    boolean findByEmail(String email);

    void removeVendedor(String clave);

    Vendedor consultarVendedor(String clave);

    Vendedor updateVendedor(Vendedor vendedorm);
    
    
}
