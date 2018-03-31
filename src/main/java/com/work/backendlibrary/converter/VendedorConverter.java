package com.work.backendlibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.VendedorModel;

@Component("vendedorConverter")
public class VendedorConverter {
	
	public VendedorModel entity2Model(Vendedor v){
		VendedorModel vm=new VendedorModel();
		vm.setClave(v.getClave());
		vm.setNombre(v.getNombre());
		vm.setApellidos(v.getApellidos());
		vm.setTelefono(v.getTelefono());
		return vm;
	}
	
	public Vendedor model2entity(VendedorModel vm){
		Vendedor v=new Vendedor();
		v.setClave(vm.getClave());
		v.setNombre(vm.getNombre());
		v.setApellidos(vm.getApellidos());
		v.setTelefono(vm.getTelefono());
		return v;
	}
	
	public List<VendedorModel> listEntity2listmodel(List<Vendedor> vendedores){
		List<VendedorModel> list = new ArrayList<VendedorModel>();
		VendedorModel vm;
		for(Vendedor vendedor: vendedores){
			vm=entity2Model(vendedor);
			list.add(vm);
		}
		
		return list;
	}
	
}
