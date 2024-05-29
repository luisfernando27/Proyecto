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
        // Tabla clientes
        db.execSQL("CREATE TABLE clientes (" +
                "idC TEXT PRIMARY KEY, " +
                "nombre TEXT, " +
                "edad INTEGER, " +
                "sexo TEXT, " +
                "correo TEXT UNIQUE, " + // correo debe ser único
                "contra TEXT, " +
                "confContra TEXT, " +
                "direccion TEXT, " +
                "ciudad TEXT)");

        // Tabla empresas
        db.execSQL("CREATE TABLE empresas (" +
                "idE TEXT PRIMARY KEY, " +
                "nombre TEXT, " +
                "correo TEXT UNIQUE, " + // correo debe ser único
                "contra TEXT, " +
                "confContra TEXT, " +
                "direccion TEXT, " +
                "ciudad TEXT)");

        // Tabla ofertasEmpresas
        db.execSQL("CREATE TABLE ofertasEmpresas (" +
                "idF TEXT PRIMARY KEY, " +
                "idE TEXT, " +
                "nombre_oferta TEXT, " +
                "precio_oferta FLOAT, " +
                "total_oferta INTEGER, " +
                "maximo_clientes INTEGER, " +
                "fecha_inicio DATE, " +
                "fecha_fin DATE, " +
                "FOREIGN KEY(idE) REFERENCES empresas(idE))");

        // Tabla ofertaCliente
        db.execSQL("CREATE TABLE ofertaCliente (" +
                "idC TEXT, " +
                "idF TEXT, " +
                "cantidad_maxima INTEGER, " +
                "PRIMARY KEY(idC, idF), " +
                "FOREIGN KEY(idC) REFERENCES clientes(idC), " +
                "FOREIGN KEY(idF) REFERENCES ofertasEmpresas(idF))");

        // Tabla productos
        db.execSQL("CREATE TABLE productos (" +
                "idP TEXT PRIMARY KEY, " +
                "idE TEXT, " +
                "nombre_producto TEXT, " +
                "descripcion TEXT, " +
                "cantidad INTEGER, " +
                "precio FLOAT, " +
                "FOREIGN KEY(idE) REFERENCES empresas(idE))");

        // Tabla ventasProducto
        db.execSQL("CREATE TABLE ventasProducto (" +
                "idVP TEXT PRIMARY KEY, " +
                "idP TEXT, " +
                "idC TEXT, " +
                "idE TEXT, " +
                "totalC FLOAT, " +
                "FOREIGN KEY(idP) REFERENCES productos(idP), " +
                "FOREIGN KEY(idC) REFERENCES clientes(idC), " +
                "FOREIGN KEY(idE) REFERENCES empresas(idE))");

        // Tabla ventasOfertas
        db.execSQL("CREATE TABLE ventasOferta (" +
                "idVO TEXT PRIMARY KEY, " +
                "idF TEXT, " +
                "idC TEXT, " +
                "idE TEXT, " +
                "totalO FLOAT, " +
                "FOREIGN KEY(idF) REFERENCES ofertasEmpresas(idF), " +
                "FOREIGN KEY(idC) REFERENCES clientes(idC), " +
                "FOREIGN KEY(idE) REFERENCES empresas(idE))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}