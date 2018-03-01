package Utilidades;

/**
 * Clase que tiene los recursos de uso común.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class Utilidades {
    // Base de datos
    public static final String DB_NAME = "DB2.db";
    public static final int DB_VERSION = 6;
    public static final String DB_TABLE = "vehiculo";

    //Query create auto.
    public static final String CREAR_AUTO = "create table  vehiculo (" +
            "_ID integer PRIMARY KEY autoincrement , Placa text , Marca text, Modelo text, Puertas integer, " +
            "Tipo text,Color text)";

    // Atributos auto
    public static final String ID = "_ID";
    public static final String PLACA = "Placa";
    public static final String MARCA = "Marca";
    public static final String MODELO = "Modelo";
    public static final String TIPO = "Tipo";
    public static final String PUERTAS = "Puertas";
    public static final String COLOR = "Color";

    // Accion intent
    public static final String ACCION = "accion";
    public static final String PLACAS = "placas";


    // Colores
    public static final String AZUL = "Azul";
    public static final String AMARILLO = "Amarillo";
    public static final String ROJO = "Rojo";
    public static final String VERDE = "Verde";
}
