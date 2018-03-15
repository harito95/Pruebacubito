package Util;

/**
 * Created by Alejandro on 14/03/2018.
 */

public abstract class Basedatos {
    private static String nombre_tabla;
    private static String consulta_creacion;
    private static int version;
    private static String columna_ID;
    private static String columna_Maximo;
    private static String dataBase_Name;

    public Basedatos() {
        this.nombre_tabla="puntuacion";
        this.columna_Maximo="Max";
        this.columna_ID="id";
        this.consulta_creacion="Create table "+nombre_tabla+"( "+columna_ID+" integer primary key, "+columna_Maximo+" integer)";
        this.version=1;
        this.dataBase_Name="puntuacion";
    }

    public static String getNombre_tabla() {
        return nombre_tabla;
    }

    public static String getConsulta_creacion() {
        return consulta_creacion;
    }

    public static String getColumna_ID() {
        return columna_ID;
    }

    public static int getVersion() {
        return version;
    }

    public static String getColumna_Maximo() {
        return columna_Maximo;
    }

    public static String getDataBase_Name() {
        return dataBase_Name;
    }

    public abstract void Guardar(int p);

    public abstract int seleccionar();

}

