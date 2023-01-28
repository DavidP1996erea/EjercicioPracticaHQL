package com.example.ejerciciopracticahql;

import jakarta.persistence.*;

@Entity
@Table(name = "Alumnos")
@NamedQueries({
        @NamedQuery(name="listaAlumnos", query="from tAlumnosHQL "),
        @NamedQuery(name="listaNombreAlumnos", query="select a.nombre from tAlumnosHQL a"),
        @NamedQuery(name="listaAlumnosPorApellido", query="select a from tAlumnosHQL a where a.apellido1 LIKE :apellido"),
        @NamedQuery(name = "numeroAlumnado", query = "select count(a) from tAlumnosHQL a")


})
public class tAlumnosHQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlumno")
    private int idAlumno;


    @Column(name ="apellido1")
    private String apellido1;

    @Column(name="apellido2")
    private String apellido2;

    @Column(name="nombre")
    private String nombre;


    public tAlumnosHQL() {

    }

    public tAlumnosHQL(String nombre, String apellido1, String apellido2) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
    }


    public int getIdAlumno() {
        return idAlumno;
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
}

