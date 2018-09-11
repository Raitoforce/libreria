package com.work.backendlibrary.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.backendlibrary.entity.Comision;

public class ComisionesVistaModel {
	private String nombre;
	private float deuda;
	private float pagado;
	private float restante;
	private String clave;
	private int iddirector;
	private int idprofesor;
	@JsonIgnore
	private List<Comision> comisiones;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getDeuda() {
		return deuda;
	}
	public void setDeuda(float deuda) {
		this.deuda = deuda;
	}
	public float getPagado() {
		return pagado;
	}
	public void setPagado(float pagado) {
		this.pagado = pagado;
	}
	public float getRestante() {
		return restante;
	}
	public void setRestante(float restante) {
		this.restante = restante;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getIddirector() {
		return iddirector;
	}
	public void setIddirector(int iddirector) {
		this.iddirector = iddirector;
	}
	public int getIdprofesor() {
		return idprofesor;
	}
	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}
	public List<Comision> getComisiones() {
		return comisiones;
	}
	public void setComisiones(List<Comision> comisiones) {
		this.comisiones = comisiones;
	}
}
