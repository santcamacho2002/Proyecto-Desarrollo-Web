package com.example.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tool")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getPrecio_referencia() {
        return precio_referencia;
    }

    public void setPrecio_referencia(Long precio_referencia) {
        this.precio_referencia = precio_referencia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name="marca")
    private String marca;

    @Column(name="precio_referencia")
    private Long precio_referencia;

    @Column(name="ciudad")
    private String ciudad;

    @Column(name="cantidad")
    private Long cantidad;
    public Tool() {

    }

    public Tool(Long id, String nombre, String descripcion, String imageUrl, String marca, Long precio_referencia, String ciudad, Long cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
        this.marca = marca;
        this.precio_referencia = precio_referencia;
        this.ciudad = ciudad;
        this.cantidad = cantidad;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
