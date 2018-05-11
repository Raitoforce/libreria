package com.work.backendlibrary.model;
import java.sql.Timestamp;

public class StockModel {

    private int idstock;

    private int cantidad;

    private Timestamp fecha_entrada;

    private int stock_actual;

    private String libro;

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

    public Timestamp getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Timestamp fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }
}
