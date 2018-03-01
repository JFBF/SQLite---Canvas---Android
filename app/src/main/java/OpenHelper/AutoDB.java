package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Utilidades.Utilidades;

/**
 * Clase que maneja las operaciones de Vehículo en la base de datos.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 * @see CrearDB
 */

public class AutoDB extends CrearDB{

    private SQLiteDatabase sqlDB;


    public AutoDB(Context ctx) {
        super(ctx);
    }

    /**
     * Método para abrir y usar la base de datos.
     * @return El manager de la base de datos.
     * @throws SQLException
     */
    public SQLiteDatabase abrir() throws SQLException {
        this.sqlDB = super.abrir();
        return sqlDB;
    }

    /**
     * Método para cerrar el manager de la base de datos.
     */
    public void cerrar() {
        this.sqlDB.close();
    }

    /**
     * Método para crear un vehículo.
     * @param placa
     * @param marca
     * @param modelo
     * @param tipo
     * @param puertas
     * @return True si inserta correctamente el nuevo elemento en la base de datos.
     */
    public boolean crearAuto(String placa, String marca, String modelo,
                          String tipo, int puertas, String color){
        long valor = 0;
        ContentValues initialValues = new ContentValues();
        initialValues.put(Utilidades.PLACA,placa.toUpperCase() );
        initialValues.put(Utilidades.MARCA, marca);
        initialValues.put(Utilidades.MODELO, modelo);
        initialValues.put(Utilidades.PUERTAS, puertas);
        initialValues.put(Utilidades.TIPO, tipo);
        if(color.isEmpty())
            initialValues.put(Utilidades.COLOR,"");
        else
            initialValues.put(Utilidades.COLOR,color);
        valor = this.sqlDB.insert(Utilidades.DB_TABLE, null, initialValues);
        return valor>0;

    }

    /**
     * Método que trae todos los atributos de la base de datos.
     * @return Un cursor en los atributos de la base de datos.
     */
    public Cursor getAllAutos() {
        Cursor cursor = null;
        cursor =  this.sqlDB.query(Utilidades.DB_TABLE, new String[] {
                Utilidades.ID,Utilidades.PLACA,Utilidades.MARCA,Utilidades.MODELO,
                Utilidades.PUERTAS,Utilidades.TIPO,Utilidades.COLOR}, null, null, null, null, null);
        return cursor;
    }

    /**
     * Método para eliminar el vehículo.
     * @param id
     * @return True si elimina correctamente el elemento seleccionado.
     */
    public boolean eliminarAuto(int id) {
        long valor = this.sqlDB.delete(Utilidades.DB_TABLE, Utilidades.ID + "=" + id, null);
        return  valor >0;
    }

    /**
     * Método para actualizar un elemento de la base de datos.
     * @param id
     * @param placa
     * @param marca
     * @param modelo
     * @param tipo
     * @param puertas
     * @return True si actualiza correctamente.
     */
    public boolean actualizarAuto(int id,String placa, String marca, String modelo,
                                  String tipo, int puertas){
        long valor = 0;
        ContentValues values = new ContentValues();
        values.put(Utilidades.PLACA, placa);
        values.put(Utilidades.MARCA, marca);
        values.put(Utilidades.MODELO, modelo);
        values.put(Utilidades.PUERTAS, puertas);
        values.put(Utilidades.TIPO, tipo);
        valor = this.sqlDB.update(Utilidades.DB_TABLE,values,Utilidades.ID + "=" + id, null);
        return valor >0;
    }

    /**
     * Método para actualizar el color de las
     * llantas de un vehículo.
     * @param id
     * @param color
     * @return True si actualiza correctamente.
     */
    public boolean actualizarColor(int id, String color){
        long valor = 0;
        ContentValues values = new ContentValues();
        values.put(Utilidades.COLOR, color);
        valor = this.sqlDB.update(Utilidades.DB_TABLE,values,Utilidades.ID + "=" + id, null);
        return valor >0;
    }

}
