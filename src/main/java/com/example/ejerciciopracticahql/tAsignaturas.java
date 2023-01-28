package com.example.ejerciciopracticahql;

import jakarta.persistence.*;

@Entity
@Table(name = "Asignaturas")
public class tAsignaturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsignatura")
    private int idAsignatura;


    @Column(name ="curso")
    private int curso;

    @Column(name="apellido2")
    private int horas;

    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idProfesores")
    private  tProfesoresHQL idProfesores;


}
