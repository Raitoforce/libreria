package com.work.backendlibrary.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="idstock")
    private int idstock;

    @Column(name="cantidad")
    private int cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_entrada")
    private Date fecha_entrada;

    @Column(name="stock_actual")
    private int stock_actual;
    
    
    @Column(name="tipomovimiento")
    private String tipomovimiento;
    
    @Column(name="motivo")
    private String motivo;

    @Column(name="hacienda", columnDefinition = "int default 0")
    private int hacienda = 0;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="clave_producto",referencedColumnName="clave_producto")
    private Libro libro;

    public int getIdstock() {
        return idstock;
    }

    public void setIdstock(int idstock) {
        this.idstock = idstock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

	public String getTipomovimiento() {
		return tipomovimiento;
	}

	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

    public int getHacienda() {
        return hacienda;
    }

    public void setHacienda(int hacienda) {
        this.hacienda = hacienda;
    }
}
