package com.work.backendlibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;

import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "vendedor")
public class Vendedor {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idVendedor")
//    private int idVendedor;

    @Id
    @Column(name = "clave", unique = true)
    private String clave;
    
    @JsonView(VentaView.interno.class)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    
    @JsonView(VentaView.interno.class)
    @NotNull
    @Column(name = "apellidos", nullable = false, length = 45)
    private String apellidos;

    @Column(name = "rfc", unique = true, length = 45)
    private String rfc;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "email", unique = true, length = 45)
    private String email;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "colonia", length = 45)
    private String colonia;

    @Column(name = "municipio", length = 45)
    private String municipio;

    @Column(name = "estado", length = 45)
    private String estado;

    @Column(name = "codigo_postal", length = 45)
    private String codigo_postal;
    
    @OneToMany(mappedBy="vendedor")
	private List<BloqueFolio> bloqueFolios;

    public Vendedor() {
    }

    public Vendedor(@NotNull String nombre, @NotNull String apellidos, String rfc,
                    String telefono, String email, String direccion, String colonia,
                    String municipio, String estado, String codigo_postal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rfc = rfc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.codigo_postal = codigo_postal;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getdireccion() {
        return direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    //@JsonIgnore
    @OneToMany(mappedBy="vendedor",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
    List<Zona> zonas;

    @Override
    public String toString() {
        return "Vendedor{" +
                "clave='" + clave + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", rfc='" + rfc + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", colonia='" + colonia + '\'' +
                ", municipio='" + municipio + '\'' +
                ", estado='" + estado + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                '}';
    }
    
    
	
	@JsonIgnoreProperties({"vendedor"})
	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	@JsonIgnoreProperties({"id","vendedor"})
    public List<BloqueFolio> getBloqueFolios() {
        return bloqueFolios;
    }

    @PreRemove
    public void Nullable(){
        for (Iterator<Zona> iterator = zonas.iterator(); iterator.hasNext();) {
            Zona zona = (Zona) iterator.next();
            zona.setVendedor(null);
        }
    }

}
