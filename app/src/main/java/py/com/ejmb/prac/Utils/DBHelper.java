package py.com.ejmb.prac.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import py.com.ejmb.prac.Classes.Curso;

/**
 * Created by Eduardo on 20/9/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "practicaDB.db";

    public static final String USUARIO_TABLE_NAME = "usuario";
    public static final String USUARIO_COLUMN_USUARIODOC = "usuariodoc";
    public static final String USUARIO_COLUMN_USUARIONOM = "usuarionom";
    public static final String USUARIO_COLUMN_USUARIOAPE = "usuarioape";

    public static final String UNIVERSIDAD_TABLE_NAME = "universidad";
    public static final String UNIVERSIDAD_COLUMN_UNIVERSIDADCOD = "universidadcod";
    public static final String UNIVERSIDAD_COLUMN_UNIVERSIDADDES = "universidaddes";

    public static final String FACULTAD_TABLE_NAME = "facultad";
    public static final String FACULTAD_COLUMN_FACULTADCOD = "facultadcod";
    public static final String FACULTAD_COLUMN_FACULTADDES = "facultaddes";

    public static final String CURSO_TABLE_NAME = "curso";
    public static final String CURSO_COLUMN_CURSOCOD = "cursocod";
    public static final String CURSO_COLUMN_CURSODES = "cursodes";
    public static final String CURSO_COLUMN_CURSOCAN = "cursocan";

    public static final String USUARIOCURSO_TABLE_NAME = "usuariocurso";
    public static final String USUARIOCURSO_COLUMN_USUCURFECH = "usucurfech";
    public static final String USUARIOCURSO_COLUMN_USUCURENTR = "usucurentr";
    public static final String USUARIOCURSO_COLUMN_USUCURSALI = "usucursali";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+USUARIO_TABLE_NAME+" ("
                +USUARIO_COLUMN_USUARIODOC+" text not null, "
                +USUARIO_COLUMN_USUARIONOM+" text, "
                +USUARIO_COLUMN_USUARIOAPE+" text, "
                +"primary key ("+USUARIO_COLUMN_USUARIODOC+"))");
        db.execSQL("create table "+UNIVERSIDAD_TABLE_NAME+" ("
                +UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" integer not null, "
                +UNIVERSIDAD_COLUMN_UNIVERSIDADDES+" text, "
                +"primary key ("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"))");
        db.execSQL("create table "+FACULTAD_TABLE_NAME+" ("
                +FACULTAD_COLUMN_FACULTADCOD+" integer not null, "
                +UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" integer not null, "
                +FACULTAD_COLUMN_FACULTADDES+" text, "
                +"primary key ("+FACULTAD_COLUMN_FACULTADCOD+"),"
                +"foreign key ("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+") references "+UNIVERSIDAD_TABLE_NAME+"("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"))");
        db.execSQL("create table "+CURSO_TABLE_NAME+" ("
                +CURSO_COLUMN_CURSOCOD+" integer not null, "
                +UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" integer not null, "
                +FACULTAD_COLUMN_FACULTADCOD+" integer not null, "
                +CURSO_COLUMN_CURSODES+" text, "
                +CURSO_COLUMN_CURSOCAN+" integer, "
                +"primary key ("+CURSO_COLUMN_CURSOCOD+"),"
                +"foreign key ("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+") references "+UNIVERSIDAD_TABLE_NAME+"("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"), "
                +"foreign key ("+FACULTAD_COLUMN_FACULTADCOD+") references "+FACULTAD_TABLE_NAME+"("+FACULTAD_COLUMN_FACULTADCOD+"))");
        db.execSQL("create table "+USUARIOCURSO_TABLE_NAME+" ("
                +USUARIOCURSO_COLUMN_USUCURFECH+" text not null, "
                +CURSO_COLUMN_CURSOCOD+" integer not null, "
                +UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" integer not null, "
                +FACULTAD_COLUMN_FACULTADCOD+" integer not null, "
                +USUARIO_COLUMN_USUARIODOC+" text not null, "
                +USUARIOCURSO_COLUMN_USUCURENTR+" text, "
                +USUARIOCURSO_COLUMN_USUCURSALI+" text, "
                +"primary key ("+USUARIOCURSO_COLUMN_USUCURFECH+"),"
                +"foreign key ("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+") references "+UNIVERSIDAD_TABLE_NAME+"("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"), "
                +"foreign key ("+FACULTAD_COLUMN_FACULTADCOD+") references "+FACULTAD_TABLE_NAME+"("+FACULTAD_COLUMN_FACULTADCOD+"), "
                +"foreign key ("+CURSO_COLUMN_CURSOCOD+") references "+CURSO_TABLE_NAME+"("+CURSO_COLUMN_CURSOCOD+"), "
                +"foreign key ("+USUARIO_COLUMN_USUARIODOC+") references "+USUARIO_TABLE_NAME+"("+USUARIO_COLUMN_USUARIODOC+"))");

        db.execSQL("insert into "+UNIVERSIDAD_TABLE_NAME+"("+UNIVERSIDAD_COLUMN_UNIVERSIDADDES+") values ('Universidad Autonoma de Asuncion')");
        db.execSQL("insert into "+FACULTAD_TABLE_NAME+"("+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+", "+FACULTAD_COLUMN_FACULTADDES+") values (1, 'Facultad de Ciencias y Tecnologia')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USUARIO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+UNIVERSIDAD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+FACULTAD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CURSO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+USUARIOCURSO_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUsuario  (String documento, String nombre, String apellido)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_COLUMN_USUARIODOC, documento);
        contentValues.put(USUARIO_COLUMN_USUARIONOM, nombre);
        contentValues.put(USUARIO_COLUMN_USUARIOAPE, apellido);
        db.insert(USUARIO_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertUniversidad  (String descripcion) //int codigo,
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // contentValues.put(UNIVERSIDAD_COLUMN_UNIVERSIDADCOD, codigo);
        contentValues.put(UNIVERSIDAD_COLUMN_UNIVERSIDADDES, descripcion);
        db.insert(UNIVERSIDAD_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertFacultad  (int codigoU, String descripcion) //int codigoF,
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(FACULTAD_COLUMN_FACULTADCOD, codigoF);
        contentValues.put(UNIVERSIDAD_COLUMN_UNIVERSIDADCOD, codigoU);
        contentValues.put(FACULTAD_COLUMN_FACULTADDES, descripcion);
        db.insert(FACULTAD_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertCurso  (int codigoU, int codigoF, String descripcion, int cantHoras) //int codigoC,
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(CURSO_COLUMN_CURSOCOD, codigoC);
        contentValues.put(UNIVERSIDAD_COLUMN_UNIVERSIDADCOD, codigoU);
        contentValues.put(FACULTAD_COLUMN_FACULTADCOD, codigoF);
        contentValues.put(CURSO_COLUMN_CURSODES, descripcion);
        contentValues.put(CURSO_COLUMN_CURSOCAN, cantHoras);
        db.insert(CURSO_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertUsuarioCurso  (String fecha, int codigoC, int codigoF, String codigoU, String entrada, String salida)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIOCURSO_COLUMN_USUCURFECH, fecha);
        contentValues.put(CURSO_COLUMN_CURSOCOD, codigoC);
        contentValues.put(FACULTAD_COLUMN_FACULTADCOD, codigoF);
        contentValues.put(USUARIO_COLUMN_USUARIODOC, codigoU);
        contentValues.put(USUARIOCURSO_COLUMN_USUCURENTR, entrada);
        contentValues.put(USUARIOCURSO_COLUMN_USUCURSALI, salida);
        db.insert(USUARIOCURSO_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getUsuario(String documento){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USUARIO_TABLE_NAME+" where "+USUARIO_COLUMN_USUARIODOC+" like '"+documento+"'", null );
        return res;
    }

    public Cursor getUniversidad(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UNIVERSIDAD_TABLE_NAME+" where "+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"="+codigo, null );
        return res;
    }

    public Cursor getUniversidadCod(String descripcion){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UNIVERSIDAD_TABLE_NAME+" where "+UNIVERSIDAD_COLUMN_UNIVERSIDADDES+" like '"+descripcion+"'", null );
        return res;
    }

    public Cursor getFacultad(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FACULTAD_TABLE_NAME+" where "+FACULTAD_COLUMN_FACULTADCOD+"="+codigo, null );
        return res;
    }

    public Cursor getFacultadCod(String descripcion){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FACULTAD_TABLE_NAME+" where "+FACULTAD_COLUMN_FACULTADDES+" like '"+descripcion+"'", null );
        return res;
    }

    public Cursor getCurso(int codigoC, int codigoF){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CURSO_TABLE_NAME+" where "+CURSO_COLUMN_CURSOCOD+"="+codigoC+" and "+FACULTAD_COLUMN_FACULTADCOD+"="+codigoF, null );
        return res;
    }

    public Cursor getUsuarioCurso(String fecha, int codigoC, int codigoF, String codigoU){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USUARIOCURSO_TABLE_NAME+" where "+USUARIOCURSO_COLUMN_USUCURFECH+" like '"+fecha+"' " +
                                    " and "+CURSO_COLUMN_CURSOCOD+"="+codigoC + " and "+FACULTAD_COLUMN_FACULTADCOD+"="+codigoF+
                                    " and "+USUARIO_COLUMN_USUARIODOC+" like '"+codigoU+"'", null );
        return res;
    }

    public int cantUsuario(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USUARIO_TABLE_NAME);
        return numRows;
    }

    public int cantUniversidad(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, UNIVERSIDAD_TABLE_NAME);
        return numRows;
    }

    public int cantFacultad(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FACULTAD_TABLE_NAME);
        return numRows;
    }

    public int cantCurso(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CURSO_TABLE_NAME);
        return numRows;
    }

    public int cantUsuarioCurso(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USUARIOCURSO_TABLE_NAME);
        return numRows;
    }

    public boolean updateUsuario (String documento, String nombre, String apellido)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_COLUMN_USUARIONOM, nombre);
        contentValues.put(USUARIO_COLUMN_USUARIOAPE, apellido);
        db.update(USUARIO_TABLE_NAME, contentValues, USUARIO_COLUMN_USUARIODOC+" = ? ", new String[] { documento } );
        return true;
    }

    public boolean updateUniversidad (int codigo, String descripcion)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIVERSIDAD_COLUMN_UNIVERSIDADDES, descripcion);
        db.update(UNIVERSIDAD_TABLE_NAME, contentValues, UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" = ? ", new String[] {Integer.toString(codigo)} );
        return true;
    }

    public boolean updateFacultad (int codigo, String descripcion)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACULTAD_COLUMN_FACULTADDES, descripcion);
        db.update(FACULTAD_TABLE_NAME, contentValues, FACULTAD_COLUMN_FACULTADCOD+" = ? ", new String[] {Integer.toString(codigo)} );
        return true;
    }

    public boolean updateCurso (int codigoC, int codigoF, String descripcion, int cantHoras)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CURSO_COLUMN_CURSODES, descripcion);
        contentValues.put(CURSO_COLUMN_CURSOCAN, cantHoras);
        db.update(FACULTAD_TABLE_NAME, contentValues, CURSO_COLUMN_CURSOCOD+" = ? and "+FACULTAD_COLUMN_FACULTADCOD+" = ? ", new String[] {Integer.toString(codigoC), Integer.toString(codigoF)} );
        return true;
    }

    public boolean updateUsuarioCurso (String fecha, int codigoC, int codigoF, String codigoU, String entrada, String salida)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIOCURSO_COLUMN_USUCURENTR, entrada);
        contentValues.put(USUARIOCURSO_COLUMN_USUCURSALI, salida);
        db.update(FACULTAD_TABLE_NAME, contentValues, USUARIOCURSO_COLUMN_USUCURFECH+" = ? and "+CURSO_COLUMN_CURSOCOD+" = ? and "+
                                                    FACULTAD_COLUMN_FACULTADCOD+" = ? and "+USUARIO_COLUMN_USUARIODOC+" = ? ",
                                                    new String[] {fecha, Integer.toString(codigoC), Integer.toString(codigoF), codigoU} );
        return true;
    }

    public Integer deleteUsuario (String documento)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USUARIO_TABLE_NAME, USUARIO_COLUMN_USUARIODOC+" = ? ", new String[] { documento });
    }

    public Integer deleteUniversidad (int codigo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UNIVERSIDAD_TABLE_NAME, UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+" = ? ", new String[] {Integer.toString(codigo)});
    }

    public Integer deleteFacultad (int codigo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FACULTAD_TABLE_NAME, FACULTAD_COLUMN_FACULTADCOD+" = ? ", new String[] {Integer.toString(codigo)});
    }

    public Integer deleteCurso (int codigoC, int codigoF)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FACULTAD_TABLE_NAME, CURSO_COLUMN_CURSOCOD+" = ? and "+FACULTAD_COLUMN_FACULTADCOD+" = ? ", new String[] {Integer.toString(codigoC), Integer.toString(codigoF)} );
    }

    public Integer deleteUsuarioCurso (String fecha, int codigoC, int codigoF, String codigoU)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FACULTAD_TABLE_NAME, USUARIOCURSO_COLUMN_USUCURFECH+" = ? and "+CURSO_COLUMN_CURSOCOD+" = ? and "+
                        FACULTAD_COLUMN_FACULTADCOD+" = ? and "+USUARIO_COLUMN_USUARIODOC+" = ? ",
                new String[] {fecha, Integer.toString(codigoC), Integer.toString(codigoF), codigoU} );
    }

    public ArrayList<String> getAllUsuarios()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USUARIO_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(USUARIO_COLUMN_USUARIONOM)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllUniversidades()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UNIVERSIDAD_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(UNIVERSIDAD_COLUMN_UNIVERSIDADDES)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllFacultades()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FACULTAD_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(FACULTAD_COLUMN_FACULTADDES)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllFacultadesUni(int uniCod)
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FACULTAD_TABLE_NAME+" where "+UNIVERSIDAD_COLUMN_UNIVERSIDADCOD+"="+uniCod, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(FACULTAD_COLUMN_FACULTADDES)));
            res.moveToNext();
        }
        return array_list;
    }

    public HashMap<String, Integer> getAllCursos()
    {
        HashMap<String, Integer> hashMap_list = new HashMap<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CURSO_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            hashMap_list.put(res.getString(res.getColumnIndex(CURSO_COLUMN_CURSODES)), res.getInt(res.getColumnIndex(CURSO_COLUMN_CURSOCAN)));
            res.moveToNext();
        }
        return hashMap_list;
    }
}
