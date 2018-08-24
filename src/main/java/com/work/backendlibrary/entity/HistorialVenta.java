package com.work.backendlibrary.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

@Entity
@Table(name="historial_venta")
public class HistorialVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonView(VentaView.Todo.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idHistorial")
	private Integer idHistorial;

	@JsonView(VentaView.Todo.class)
	private Integer entregados;

	@JsonView(VentaView.Todo.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_confirmacion")
	private Date fechaConfirmacion;

	@JsonView(VentaView.Todo.class)
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_solicitud")
	private Date fechaSolicitud;

	@JsonView(VentaView.Todo.class)
	@Column(name="motivo")
	private String motivo;

	@JsonView(VentaView.Todo.class)
	@Column(name="pedidos")
	private int pedidos;

	@JsonView(VentaView.Todo.class)
	@Column(name="tipo_movimiento")
	private String tipoMovimiento;
	
	@JsonView(VentaView.interno.class)
	@Column(name="precioventa")
	private Float precioventa;
	
	@JsonView(VentaView.interno.class)
	@Column(name="numresurtido")
	private Integer numresurtido;

	@JsonView(VentaView.Todo.class)
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="venta_folio",referencedColumnName="folio")
	private Venta venta;
	
	@JsonIgnore
	@OneToMany(mappedBy="historialVenta")
	private List<CuentasPorCobrar> cuentas;
	
	@JsonView(VentaView.Todo.class)
	@ManyToOne
	@JoinColumn(name="libro_clave_producto",referencedColumnName="clave_producto")
	private Libro libro;
	
	public HistorialVenta() {
	
	}
	
	public int getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Integer getEntregados() {
		return entregados;
	}

	public void setEntregados(Integer entregados) {
		this.entregados = entregados;
	}

	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getPedidos() {
		return pedidos;
	}

	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Integer getNumresurtido() {
		return numresurtido;
	}

	public void setNumresurtido(Integer numresurtido) {
		this.numresurtido = numresurtido;
	}

	public Float getPrecioventa() {
		return precioventa;
	}

	public void setPrecioventa(Float precioventa) {
		this.precioventa = precioventa;
	}
	
	public List<CuentasPorCobrar> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentasPorCobrar> cuentas) {
		this.cuentas = cuentas;
	}

	public float CalcularDeuda(){
		float subtotalC=0;
		float descuentoC=0;
		float descLider = 0;
		if(this.getPrecioventa()!=0){
				subtotalC+=this.getPrecioventa()*this.getPedidos();
				descuentoC+=this.getPedidos()*this.getVenta().getComisionProfesor();
				for(LideresComisiones lider:getVenta().getLideres()){
					descLider+=this.getVenta().getLiderComision(lider.getLider().getIdprofesor());
				}
				descuentoC+=descLider;
				descLider = 0;
		}
		return subtotalC-descuentoC;
	}
}