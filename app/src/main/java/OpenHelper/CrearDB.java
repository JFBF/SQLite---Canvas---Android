package OpenHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Utilidades.Utilidades;

/**
 * Clase que crea la Base de datos con DatasabeHelper.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */
public class CrearDB {

    private final Context context;
    protected DatabaseHelper helper;
    private SQLiteDatabase sqlDB;

    /**
     * Clase para manejar la base de datos.
     * @author Felipe Bautista
     * @version 22/02/2018
     * @since 1.0
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context){
            super(context, Utilidades.DB_NAME, null, Utilidades.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(Utilidades.CREAR_AUTO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("drop table if exists "+Utilidades.DB_TABLE);
            onCreate(db);
        }
    }

    /**
     * Método que instancia la base de datos.
     * @param ctx
     */
    public CrearDB(Context ctx)
    {
        this.context = ctx;
        this.helper = new DatabaseHelper(this.context);
    }

    /**
     * Método para permitir escribir en la base de datos.
     * @return La base de datos creada (Manager).
     * @throws SQLException
     */
    protected  SQLiteDatabase abrir() throws SQLException
    {
        return this.helper.getWritableDatabase();

    }

    /**
     * Método para cerrar la base de datos.
     */
    public void cerrar()
    {
        this.helper.close();
    }
}
