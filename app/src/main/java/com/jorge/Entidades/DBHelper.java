package com.jorge.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.jorge.Datos.Alumno;

import java.util.ArrayList;

/**
 * Created by zero_ on 22/5/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bd_alumnos";
    public static final String TABLA_ALUMNO = "Alumno";
    public static final String CAMPO_ID = "carnet";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_NOTA = "nota";
    public static final String CREAR_TABLA_ALUMNOS = "CREATE TABLE "+
            TABLA_ALUMNO + "(" +
            CAMPO_ID + " TEXT," +
            CAMPO_NOMBRE + " TEXT," +
            CAMPO_NOTA + " TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    //Metodos

    public static DBHelper getInstance(Context ctx){
        if (myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    public boolean addAlumno(Alumno a){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID, a.getCarnet());
        values.put(CAMPO_NOMBRE, a.getNombre());
        values.put(CAMPO_NOTA, a.getNota());
        db.insert(TABLA_ALUMNO, null, values);
        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Alumno buscarAlumno(String carnet){

        Alumno a;

        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOMBRE, CAMPO_NOTA};

        try{
            Cursor cursor = db.query(TABLA_ALUMNO, campos, CAMPO_ID+"=?", parametros,
                    null, null, null);
            cursor.moveToFirst();

            a = new Alumno(carnet, cursor.getString(0), cursor.getFloat(1));
        }
        catch (Exception e){
            a = null;
        }
        return a;
    }

    public boolean editUser(Alumno a){

        String[] parametros = {a.getCarnet()};

        ContentValues values= new ContentValues();
        values.put(CAMPO_NOMBRE,a.getNombre());
        values.put(CAMPO_NOTA, a.getNota());

        db.update(TABLA_ALUMNO, values, CAMPO_ID+"=?", parametros);

        Toast.makeText(context, "Usuario actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean deleteUser(String carnet){
        String[] parametros = {carnet};
        db.delete(TABLA_ALUMNO,CAMPO_ID+"=?", parametros);
        Toast.makeText(context, "Usuario eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList<Alumno> getAlumnos(){

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_ALUMNO,null);
        ArrayList<Alumno> a = new ArrayList<Alumno>();

        while (cursor.moveToNext()){
            a.add(new Alumno(cursor.getString(0),cursor.getString(1),
                    Float.parseFloat(cursor.getString(2))));
        }

        return a;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ CAMPO_NOMBRE);
        onCreate(db);
    }
}
