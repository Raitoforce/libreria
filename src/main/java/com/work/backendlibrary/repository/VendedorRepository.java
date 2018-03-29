package com.work.backendlibrary.repository;

import com.work.backendlibrary.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("vendedorRepository")
public interface VendedorRepository extends JpaRepository<Vendedor, Serializable> {
    Vendedor findByClave(String clave);

    Vendedor findByRfc(String rfc);

    Vendedor findByEmail(String email);
}
