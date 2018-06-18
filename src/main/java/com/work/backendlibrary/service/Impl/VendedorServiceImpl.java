package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.converter.VendedorConverter;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.VendedorModel;
import com.work.backendlibrary.repository.VendedorRepository;
import com.work.backendlibrary.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("vendedorServiceImpl")
public class VendedorServiceImpl implements VendedorService {
    @Autowired
    @Qualifier("vendedorRepository")
    private VendedorRepository vendedorRepository;
    
    @Autowired
	@Qualifier("vendedorConverter")
	private VendedorConverter vendedorc;

    @Override
    public Vendedor addVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Boolean findByClave(String clave) {
        return vendedorRepository.findByClave(clave) != null;
    }

    @Override
    public Boolean findByRfc(String rfc) {
        return vendedorRepository.findByRfc(rfc) != null;
    }

    @Override
    public List<Vendedor> listAll() {
        return vendedorRepository.findAll();
    }

    @Override
    public boolean findByEmail(String email) {
        return vendedorRepository.findByEmail(email) != null;
    }

    @Override
    public void removeVendedor(String clave) {
        vendedorRepository.deleteById(clave);
    }

    @Override
    public Vendedor consultarVendedor(String clave) {
        Vendedor vendedorm = vendedorRepository.findByClave(clave);
        return vendedorm;
    }

    @Override
    public Vendedor updateVendedor(Vendedor vendedorm) {
        return vendedorRepository.save(vendedorm);
    }
	@Override
	public List<VendedorModel> listAllModel() {
		List<VendedorModel> vm= vendedorc.listEntity2listmodel(vendedorRepository.findAll());
		return vm;
	}

	@Override
	public List<VendedorModel> listPage(Pageable pageable) {
		return vendedorc.listEntity2listmodel(vendedorRepository.findAll(pageable).getContent());
	}

}
