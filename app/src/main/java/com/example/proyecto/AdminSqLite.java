package com.example.proyecto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqLite extends SQLiteOpenHelper{


    public AdminSqLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table clientes(nombre varchar, edad int, sexo varchar, correo text primary key, contra text, confContra text, direccion text, ciudad text)");

        db.execSQL("create table empresas(nombre varchar, correo text primary key, contra text, confContra text, direccion text, ciudad text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}