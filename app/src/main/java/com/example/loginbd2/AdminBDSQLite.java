package com.example.loginbd2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

public class AdminBDSQLite extends SQLiteOpenHelper {


    public AdminBDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(cedula int primary key, nombre text, apellido text, telefono int, correo text, password text)");
        db.execSQL("create table consultas(id integer primary key autoincrement, especialidad text, doctores text, dias text, estado text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS consultas");
        onCreate(db);

    }
}
