package Negocio;

import android.database.Cursor;

import java.util.ArrayList;

import Entidades.Vehiculo;
import OpenHelper.AutoDB;

/**
 * Clase con lógica de negocio.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */
public class Quileia {

    /**
     * Método que llena los vehículos registrados en la base de datos.
     * @param autoDB
     * @return la lista con los vehículos en la bd.
     */
    public static ArrayList<Vehiculo> leer (AutoDB autoDB){
        ArrayList<Vehiculo> vehiculos = new ArrayList();
        autoDB.abrir();
        Cursor cursor = autoDB.getAllAutos();
        while(cursor.moveToNext()){
            Vehiculo v = new Vehiculo();
            v.setId(cursor.getInt(0));
            v.setPlaca(cursor.getString(1));
            v.setMarca(cursor.getString(2));
            v.setModelo(cursor.getString(3));
            v.setPuertas(cursor.getInt(4));
            v.setTipo(cursor.getString(5));
            v.setColor(cursor.getString(6));
            vehiculos.add(v);
        }
        autoDB.cerrar();
        return vehiculos;
    }

    /**
     * Método que actualiza el color de las llantas de un vehículo.
     * @return True si lo actualiza correctamente.
     */
    public static boolean actualizarAutoColor(AutoDB autoDB,int id, String llantasColor){
        autoDB.abrir();
        boolean valor = autoDB.actualizarColor(id,llantasColor);
        autoDB.cerrar();
        return valor;
    }

    /**
     * Método para crear un vehículo.
     * @param autoDB
     * @param placa
     * @param marca
     * @param modelo
     * @param tipo
     * @param puertas
     * @param color
     * @return true si crea el vehículo correctamente.
     */
    public static boolean crearAuto(AutoDB autoDB,String placa,
                                    String marca, String modelo,
                                    String tipo, int puertas, String color){
        autoDB.abrir();
        boolean valor = autoDB.crearAuto(placa,
                marca,modelo,tipo,puertas,color);
        autoDB.cerrar();
        return valor;
    }

    /**
     * Método para actualizar el vehículo.
     * @param autoDB
     * @param id
     * @param placa
     * @param marca
     * @param modelo
     * @param tipo
     * @param puertas
     * @return true si hace la actualización correctamente.
     */
    public static boolean actualizarAuto(AutoDB autoDB,int id,String placa,
                                   String marca, String modelo,
                                   String tipo, int puertas){
        autoDB.abrir();
        boolean valor = autoDB.actualizarAuto(id,placa,
                marca,modelo,tipo,puertas);
        autoDB.cerrar();
        return valor;
    }

    /**
     * Método para eliminar un vehículo dado su ID.
     * @param autoDB
     * @param id
     * @return True si lo elimina correctamente.
     */
    public static boolean eliminarAuto(AutoDB autoDB,int id){
        return autoDB.eliminarAuto(id);
    }
}
