package com.example.ejerciciopracticahql;

import jakarta.persistence.*;

@Entity
@Table(name = "tProfesoresHQL")
@NamedQueries({
        @NamedQuery(name="listaProfesores", query="from tProfesoresHQL")})

public class tProfesoresHQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProfesores")
    private int idProfesores;


    @Column(name = "apellido1")
    public String apellido1;

    @Column(name = "apellido2")
    public String apellido2;

    @Column(name = "nombre")
    public String nombre;


    public tProfesoresHQL() {
    }

    public tProfesoresHQL(String nombre, String apellido1, String apellido2) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
    }

    public void setIdProfesores(int idProfesores) {
        this.idProfesores = idProfesores;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return nombre + " " + apellido1 + " " + apellido2;
    }
}