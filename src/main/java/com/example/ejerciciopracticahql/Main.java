package com.example.ejerciciopracticahql;

import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            Conexiones.setUp();
            Conexiones.session=Conexiones.sessionFactory.openSession();

     /*       Scanner sc2 = new Scanner(System.in);
            // Se piden los datos al usuario
            System.out.println("Introduce su nombre");
            String nombre = sc2.nextLine();
            System.out.println("Introduce su apellido1");
            String apellido1 = sc2.nextLine();
            System.out.println("Introduce su apellido2");
            String apellido2 = sc2.nextLine();
            insertarAlumno(nombre, apellido1, apellido2);

*/
            listar();


            Conexiones.sessionFactory.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    private static void insertarProfesor(String nombre, String apellido1, String apellido2 ) {


        tProfesoresHQL profesor = new tProfesoresHQL(nombre,apellido1,apellido2);
        Transaction transaction = Conexiones.session.beginTransaction();
        int id = (int) Conexiones.session.save(profesor);
        transaction.commit();
        System.out.println(id);

    }


    private static void insertarAlumno(String nombre, String apellido1, String apellido2 ) {


        tAlumnosHQL profesor = new tAlumnosHQL(nombre,apellido1,apellido2);
        Transaction transaction = Conexiones.session.beginTransaction();
        int id = (int) Conexiones.session.save(profesor);
        transaction.commit();
        System.out.println(id);

    }


    //    Consulta genérica
    public static void listar() {


        List <tProfesoresHQL> lista = listarNamedQueries("listaProfesores");


        for(int i =0; i< lista.size();i++){

            System.out.println( lista.get(i));
        }
    }


    //    Consulta genérica
    public static List listarNamedQueries(String namedQuery) {
        TypedQuery lista = Conexiones.session.getNamedQuery(namedQuery);
        return lista.getResultList();
    }
}
