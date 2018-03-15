package com.mygdx.screengame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class CreadorBD extends SQLiteOpenHelper {
    public CreadorBD(Context context) {
        super(context, Basededatoandroid.getDataBase_Name(), null, Basededatoandroid.getVersion());
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Basededatoandroid.getConsulta_creacion());

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}