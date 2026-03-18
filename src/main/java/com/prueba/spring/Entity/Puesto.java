package com.prueba.spring.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "PUESTO")
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="puestoid")
    private int puestoid;
    
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    public int getPuestoid() {
        return puestoid;
    }

    public void setPuestoid(int puestoid) {
        this.puestoid = puestoid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
