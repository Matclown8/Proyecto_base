package com.example.proyectobase.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    //Constructor que genera la instancia de mi database ....

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Permite crear el modelo ralacional de base de datos (tablas,campos,etc...)
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE clases (codigo int primary key, clases text, intensidad text)");

    }

    // Permite realizar cambios esquematicos a mi modelo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
