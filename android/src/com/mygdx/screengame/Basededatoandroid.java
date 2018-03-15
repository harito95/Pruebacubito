package com.mygdx.screengame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Util.Basedatos;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class Basededatoandroid extends Basedatos {
    private Context context;
    public static int puntuacion;
    public Basededatoandroid(Context t) {
        this.context = t;
    }

    /**
     * Guardar en la base de datos
     */

    @Override
    public void Guardar(int p) {

        CreadorBD cb = new CreadorBD(context);

        SQLiteDatabase dtb = cb.getWritableDatabase();


        Cursor b = dtb.rawQuery("select * from '" + getNombre_tabla() + "';", null);

        boolean cambio = false;

        if (b.getCount() <= 0) {
            ContentValues cv = new ContentValues();
            cv.put(getColumna_ID(), "0");
            cv.put(getColumna_Maximo(), p);
            dtb.insert(getNombre_tabla(), null, cv);
        }
        b = dtb.rawQuery("select * from '" + getNombre_tabla() + "';", null);
        b.moveToFirst();
        if (b.getInt(0) <= p) {

            cambio = true;
        }
        if (cambio) {
            dtb.execSQL("delete from " + getNombre_tabla());
            dtb.rawQuery("delete from '" + getNombre_tabla() + "';", null);
            ContentValues cv = new ContentValues();
            cv.put(getColumna_Maximo(), p);
            dtb.insert(getNombre_tabla(), null, cv);
        }
    }

    /**
     * seleccionar un elementod ela base de datos
     */

    public int seleccionar() {
        CreadorBD cb = new CreadorBD(context);
        SQLiteDatabase dtb = cb.getReadableDatabase();
        String[] tableColumns = new String[]{
                getColumna_ID(), getColumna_Maximo()
        };

        Cursor c = dtb.query(getNombre_tabla(), tableColumns, getColumna_Maximo(), null,
                null, null, null);
        if (c.getCount() <= 0) {
            puntuacion = 0;
            Guardar(0);
        }
        c = dtb.query(getNombre_tabla(), tableColumns, getColumna_Maximo(), null,
                null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            puntuacion = c.getInt(1);
        }
        return puntuacion;
    }
    public static int getPuntuacionMaxima() {
        return puntuacion;
    }
}
