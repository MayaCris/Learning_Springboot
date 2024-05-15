package com.tallerPlatzi.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CATEGORIAS")
public class Categoria {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id_categoria")
    private Integer idCategoria;

    private String descripcion;

    private Byte estado;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

//Getters and Setters


    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }
}
