package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.BloqueFolio;
import com.work.backendlibrary.repository.BloqueFoliosJPARepository;
import com.work.backendlibrary.service.BloqueFolioService;

@Service("bloqueFolioService")
public class BloqueFolioServiceImpl implements BloqueFolioService{
	@Autowired
	@Qualifier("bloqueFolioJPARepository")
	BloqueFoliosJPARepository bfJPA;
	
	@Override
	public List<BloqueFolio> listAllBloqueFolios() {
		// TODO Auto-generated method stub
		return bfJPA.findAll();
	}

	@Override
	public BloqueFolio addBloqueFolio(BloqueFolio bf) {
		// TODO Auto-generated method stub
		return bfJPA.save(bf);
	}

	@Override
	public void removeBloqueFolio(String clave,int id) {
		// TODO Auto-generated method stub
		bfJPA.deleteByClaveAndId(clave, id);
	}

	@Override
	public BloqueFolio updateFolio(BloqueFolio bf) {
		// TODO Auto-generated method stub
		return bfJPA.save(bf);
	}

	@Override
	public BloqueFolio consultarFolio(String clave,int id) {
		// TODO Auto-generated method stub
		return bfJPA.findByVendedorClaveAndFolioIdfolios(clave,id);
	}

	@Override
	public boolean isInRange(int valor, int idfolio) {
		if(bfJPA.findByIsRange(valor, idfolio)!=null)
			return true;
		return false;
	}

	@Override
	public BloqueFolio consultarByVendedorAndTemporada(String clave, int idtemporada) {
		// TODO Auto-generated method stub
		return bfJPA.findByVendedorClaveAndFolioIdtemporada(clave, idtemporada);
	}

	@Override
	public BloqueFolio isInRangeAndVendedor(String clave, int valor) {
		return bfJPA.findByVendedorAndRange(clave, valor);
	}

	@Override
	public boolean isAValidFolio(String clave, int valor) {
		// TODO Auto-generated method stub
		if(isInRangeAndVendedor(clave, valor)!=null)
			return true;
		return false;
	}
}
