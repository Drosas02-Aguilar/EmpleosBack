
package com.prueba.spring.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLEADO")
public class Empleado {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="empleadoid")
private int empleadoid;

@Column(name= "nombre", nullable = false, length = 150)
private String nombre;

@ManyToOne
@JoinColumn(name= "departamentoid")
private Departamento departamento;

@ManyToOne
@JoinColumn(name= "puestoid")
private Puesto puesto;

    public int getEmpleadoid() {
        return empleadoid;
    }

    public void setEmpleadoid(int empleadoid) {
        this.empleadoid = empleadoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    


    
}
