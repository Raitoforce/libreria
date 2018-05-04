package com.work.backendlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.backendlibrary.entity.CuentasPorCobrar;
import com.work.backendlibrary.repository.CuentasPorCobrarJPARepository;
import com.work.backendlibrary.service.CuentasPorCobrarService;

@Service("cuentasPorCobrarService")
public class CuentasPorCobrarServiceImpl implements CuentasPorCobrarService{
	@Autowired
	@Qualifier("cuentasPorCobrarJPARepository")
	CuentasPorCobrarJPARepository cpcJPA;
	
	@Override
	public List<CuentasPorCobrar> listAllCuentas() {
		// TODO Auto-generated method stub
		return cpcJPA.findAll();
	}

	@Override
	public CuentasPorCobrar addCuenta(CuentasPorCobrar cuenta) {
		// TODO Auto-generated method stub
		return cpcJPA.save(cuenta);
	}

	@Override
	public void removeCuenta(int idmoviento) {
		// TODO Auto-generated method stub
		cpcJPA.deleteById(idmoviento);
	}

	@Override
	public CuentasPorCobrar updateCuenta(CuentasPorCobrar cuenta) {
		// TODO Auto-generated method stub
		return cpcJPA.save(cuenta);
	}

	@Override
	public CuentasPorCobrar consultarCuenta(int idmovimiento) {
		// TODO Auto-generated method stub
		return cpcJPA.findByIdMovimiento(idmovimiento);
	}

}
