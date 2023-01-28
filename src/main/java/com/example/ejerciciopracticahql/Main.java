package com.example.ejerciciopracticahql;


import jakarta.persistence.TypedQuery;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        /**
         * Codigo para deshabilitar los warnings de hibernate
         */
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

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


    /**
     * Método para insertar en la base de datos
     * @param nombre
     * @param apellido1
     * @param apellido2
     */
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


    /**
     * Método para listar de forma genérica, se cambia el tipo de List y la sentencia HQL o bien
     * el NamedQuery
     */
    public static void listar() {

        /* Ejemplo NamedQuery*/
        List <tProfesoresHQL> lista = listarNamedQueries("listaProfesores");


        /* Ejemplo select nombre en NamedQuery
            List <tAlumnosHQL> lista = listarNamedQueries("listaNombreAlumnos");
        */

        /* Ejemplo de consulta
        List <tAlumnosHQL> lista = Conexiones.session.createQuery("from tAlumnosHQL WHERE ").getResultList();
         */

        /* Listado usando un parámetro, primero se le pasa el nombre de la NamedQuery, luego el nombre
        del parámetro puesto en la clase, en este caso apellido,y por último será el valora buscar

        List <tAlumnosHQL> lista = listarConParametro("listaAlumnosPorApellido","apellido","Muletas");
          */
        /* Ejemplo de namedQuert que devuelve un unique valor
        List <tAlumnosHQL> lista = listarNamedQueries("numeroAlumnado");
           System.out.println( resultado("numeroAlumnado"));
        */

        for(int i =0; i< lista.size();i++){

            System.out.println( lista.get(i));
        }
    }


    /**
     * Método que se necestia para listar, recibe como parámetro los NamedQuery creados
     * en las clases
     * @param namedQuery
     * @return
     */
    public static List listarNamedQueries(String namedQuery) {
        TypedQuery lista = Conexiones.session.getNamedQuery(namedQuery);
        return lista.getResultList();
    }


    /**
     * Método que recibe el nombre de la Namedquery, del parámetro que hayamos puesto en la clase,
     * y del valora buscar
     * @param namedQuery
     * @param param
     * @param valor
     * @return
     */
    public static List listarConParametro(String namedQuery,String param, String valor) {
        TypedQuery lista = Conexiones.session.getNamedQuery(namedQuery).setParameter(param,valor);
        return lista.getResultList();
    }

    /**
     * Método que devuelve el valor de las consultas de agregación, pueden ser:
     * avg(...)
     * sum(...)
     * min(...)
     * max(...)
     * count(*), count(...), count(distinct ...), count(all...)
     * @param namedQuery
     * @return
     */
    public static long resultado(String namedQuery) {

        Query lista = Conexiones.session.getNamedQuery(namedQuery);

        // Para el uso del uniqueResult se importa import org.hibernate.query.Query;
        return (long)lista.uniqueResult();
    }
}
