package com.example.vacunacionshark.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sesion extends SQLiteOpenHelper {

    private static final String s_nombre_bd= "sesion.db";
    private static final int i_version_bd=1;
    private static final String s_tabla_usuario="create table if not exists USUARIO(ID INTEGER,CORREO VARCHAR(50),CONGRASENA VARCHAR(50))";


    public Sesion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, s_nombre_bd, factory, i_version_bd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(s_tabla_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists USUARIO");
        db.execSQL(s_tabla_usuario);
    }

    public boolean agregarUsuario(int id,String correo,String clave){
        boolean b_agregado=false;
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            db.execSQL("insert into USUARIO values("+id+",'"+correo+"','"+clave+"');");
            db.close();
            b_agregado=true;
        }


        return b_agregado;
    }

    public boolean recordoSesion(){
        boolean bRecordo=false;
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            Cursor cursor = db.rawQuery("select * from USUARIO;",null);
            if(cursor.moveToNext())
            bRecordo=true;
        }

        return  bRecordo;
    }

    public String recuperarCampo(String campo){
        String s_data=null;
        SQLiteDatabase db = getWritableDatabase();
        String sConsulta= String.format("select %s from USUARIO",campo);

        if(db!=null){
            Cursor cursor = db.rawQuery(sConsulta,null);
            if(cursor.moveToNext())
                s_data=cursor.getString(0);

        }
        return  s_data;
    }

}
