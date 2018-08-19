package com.work.backendlibrary.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.work.backendlibrary.entity.LideresComisiones;
import com.work.backendlibrary.entity.LideresComisionesPK;

public class VentaModel {
	private String folio;
	private Date fecha;
	private float comision_vendedor;
	private float comision_profesor;
	private float comision_director;
	private String vendedor_clave;
	private int idfolios;
	private String escuela_clave;
	private int idprofesor;
	private List<HistorialVentaModel> pedidos;
	private List<Lider> lideres; 
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getComision_vendedor() {
		return comision_vendedor;
	}
	public void setComision_vendedor(float comision_vendedor) {
		this.comision_vendedor = comision_vendedor;
	}
	public float getComision_profesor() {
		return comision_profesor;
	}
	public void setComision_profesor(float comision_profesor) {
		this.comision_profesor = comision_profesor;
	}
	public float getComision_director() {
		return comision_director;
	}
	public void setComision_director(float comision_director) {
		this.comision_director = comision_director;
	}
	public String getVendedor_clave() {
		return vendedor_clave;
	}
	public void setVendedor_clave(String vendedor_clave) {
		this.vendedor_clave = vendedor_clave;
	}
	public int getIdfolios() {
		return idfolios;
	}
	public void setIdfolios(int idfolios) {
		this.idfolios = idfolios;
	}
	public String getEscuela_clave() {
		return escuela_clave;
	}
	public void setEscuela_clave(String escuela_clave) {
		this.escuela_clave = escuela_clave;
	}
	public int getIdprofesor() {
		return idprofesor;
	}
	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
		
	}
	
	public List<HistorialVentaModel> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<HistorialVentaModel> pedidos) {
		this.pedidos = pedidos;
	}
	
	public List<Lider> getLideres() {
		return lideres;
	}
	public void setLideres(List<Lider> lideres) {
		this.lideres = lideres;
	}
	public VentaModel(String folio, Date fecha, float comision_vendedor, float comision_profesor,
			float comision_director, String vendedor_clave, int idfolios, String escuela_clave, int idprofesor) {
		super();
		this.folio = folio;
		this.fecha = fecha;
		this.comision_vendedor = comision_vendedor;
		this.comision_profesor = comision_profesor;
		this.comision_director = comision_director;
		this.vendedor_clave = vendedor_clave;
		this.idfolios = idfolios;
		this.escuela_clave = escuela_clave;
		this.idprofesor = idprofesor;
	}
	
	public VentaModel(){}
	
	public void setNumResurtido(int num){
		for (HistorialVentaModel pedido : pedidos) {
			pedido.setNumresurtido(num);
		}
	}
	
	public List<LideresComisiones> lider2entity(){
		List<LideresComisiones> lideresC = new ArrayList<>();
		LideresComisiones liderC;
		for (Lider lider : lideres) {
			liderC = new LideresComisiones();
			liderC.setComision_lider(lider.getComision_lider());
			liderC.setId(new LideresComisionesPK(lider.getLider(),folio));
			lideresC.add(liderC);
		}
		
		return lideresC;
	}
}

class Lider{
	private int lider;
	private float comision_lider;
	
	
	public int getLider() {
		return lider;
	}
	public void setLider(int lider) {
		this.lider = lider;
	}
	public float getComision_lider() {
		return comision_lider;
	}
	public void setComision_lider(float comision_lider) {
		this.comision_lider = comision_lider;
	}
}
