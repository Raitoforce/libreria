package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Null;

import org.springframework.data.querydsl.binding.QuerydslPredicate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

@Entity
@Table(name="venta")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonView(VentaView.Todo.class)
	@Id
	@Column(name="folio")
	private String folio;
	
	@JsonView(VentaView.interno.class)
	@Column(name="comision_director")
	private float comisionDirector;

	@JsonView(VentaView.interno.class)
	@Column(name="comision_profesor")
	private float comisionProfesor;

	@JsonView(VentaView.interno.class)
	@Column(name="comision_vendedor")
	private float comisionVendedor;

	@JsonView(VentaView.interno.class)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@JsonView(VentaView.interno.class)
	@Column(name="hacienda", columnDefinition = "int default 0")
	private int hacienda = 0;

	//bi-directional many-to-one association to BloqueFolio
	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id", referencedColumnName="id"),
		@JoinColumn(name="idfolios", referencedColumnName="folio_idfolios"),
		@JoinColumn(name="vendedor_clave", referencedColumnName="vendedor_clave")
		})
	private BloqueFolio bloqueFolio;

	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumn(name="escuela_clave",referencedColumnName="clave")
	private Escuela escuela;

	@JsonView(VentaView.interno.class)
	@ManyToOne
	@JoinColumn(name="idprofesor",referencedColumnName="idprofesor")
	private Profesor profesor;
	
	@OneToMany(mappedBy="venta",cascade=CascadeType.ALL)
	private Set<HistorialVenta> pedidos= new LinkedHashSet<>();
	
	
	@JsonView(VentaView.interno.class)
	@OneToMany(cascade= CascadeType.ALL,mappedBy="venta",orphanRemoval=true)
	@JsonIgnoreProperties({"venta"})
	private List<LideresComisiones> lideres;
	
	@JsonView(VentaView.interno.class)
	@Transient
	private float deuda;
	
	@JsonView(VentaView.interno.class)
	@Transient 
	private float pagado;
	
	@JsonView(VentaView.interno.class)
	@Transient
	private float restante;
	
	@Transient
	@JsonIgnore
	private Set<CuentasPorCobrar> cuentas=new HashSet<>();
	
	@Transient
	@JsonIgnore
	private float comisionesLider;
	
	@Transient
	@JsonIgnore
	private int librosvendidos = gettotalLibros();
	
	public Venta(){
	}

	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public float getComisionDirector() {
		return this.comisionDirector;
	}

	public void setComisionDirector(float comisionDirector) {
		this.comisionDirector = comisionDirector;
	}

	public float getComisionProfesor() {
		return this.comisionProfesor;
	}

	public void setComisionProfesor(float comisionProfesor) {
		this.comisionProfesor = comisionProfesor;
	}

	public float getComisionVendedor() {
		return this.comisionVendedor;
	}

	public void setComisionVendedor(float comisionVendedor) {
		this.comisionVendedor = comisionVendedor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BloqueFolio getBloqueFolio() {
		return this.bloqueFolio;
	}

	public void setBloqueFolio(BloqueFolio bloqueFolio) {
		this.bloqueFolio = bloqueFolio;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@JsonIgnore
	public Set<HistorialVenta> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<HistorialVenta> pedidos) {
		this.pedidos = pedidos;
	}

	public List<LideresComisiones> getLideres() {
		return lideres;
	}

	public void setLideres(List<LideresComisiones> lideres) {
		this.lideres = lideres;
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

	public int getHacienda() {
		return hacienda;
	}

	public void setHacienda(int hacienda) {
		this.hacienda = hacienda;
	}

	public void setCuentas(Set<CuentasPorCobrar> cuentas) {
		this.cuentas = cuentas;
	}

	public int getLibrosvendidos() {
		return librosvendidos;
	}

	public void setLibrosvendidos(int librosvendidos) {
		this.librosvendidos = librosvendidos;
	}

	public void setRestante(float restante) {
		this.restante = restante;
	}

	public float getLiderComision(int lider){
		float comision = 0;
		for (LideresComisiones lideresComisiones : lideres) {
			if(lideresComisiones.getLider().getIdprofesor()==lider){
				comision = lideresComisiones.getComision_lider();
				break;
			}
		}
		return comision;
	}
	
	public float getLiderComisionTotal(int lider){
		float comision = 0;
		for (HistorialVenta pedido : pedidos){
			if(pedido.getPrecioventa()!=0){
				comision+= pedido.getPedidos()*this.getLiderComision(lider);
			}
		}
		return comision;
	}


	@PreRemove
	public void Nullable(){
		for (HistorialVenta pedido: pedidos){
			pedido=null;
		}
	}
	
	public void calcularDeuda(){
		float total = 0;
		for (HistorialVenta pedido :getPedidos()) {
			total+=pedido.CalcularDeuda();
		}
		this.setDeuda(total);
	}
	
	public void calcularPagado(){
		float total = 0;
		for (HistorialVenta pedido :getPedidos()) {
			for (CuentasPorCobrar cpc:pedido.getCuentas()) {
				total+=cpc.getPago();
			}
		}
		this.setPagado(total);
	}
	
	public void calcularAll(){
		this.calcularDeuda();
		this.calcularPagado();
		this.setRestante(this.getDeuda()-this.getPagado());
	}
	
	public Set<CuentasPorCobrar> getCuentas(){
		for (HistorialVenta historial : pedidos) {
			cuentas.addAll(historial.getCuentas());
		}
		return this.cuentas;
	}
	
	public float getComisionesLideres(){
		this.comisionesLider=0;
		for (LideresComisiones lider : this.getLideres()) {
			comisionesLider+=getLiderComision(lider.getId().getProfesor());
		}
		return this.comisionesLider;
	}
	
	public int gettotalLibros(){
		int total=0;
		for(HistorialVenta pedido:this.pedidos){
			if(pedido.getPrecioventa()!=0) //si es diferente de 0 se suman
				total+= pedido.getPedidos();
		}
		return total;
		
	}

	public void setComisionesLider(float comisionesLider) {
		this.comisionesLider = comisionesLider;
	}

	public float getComisionesLider() {
		return comisionesLider;
	}

}
