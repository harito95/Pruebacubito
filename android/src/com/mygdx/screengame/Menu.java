package com.mygdx.screengame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class Menu extends Activity {

    private Audio ad;
    private Button button2;
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private int cont;
    private Button button3;
    private Button butonmusica;
    private TextView textscore;
    private Basededatoandroid mb;

    /**
     * Declaracion de botones y textos
     */

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        mb=new Basededatoandroid(this);
        setContentView(R.layout.layout);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textscore =findViewById(R.id.textscore);
        butonmusica=findViewById(R.id.butonmusica);
        textscore.setText(""+mb.seleccionar());
        ad= new Audio();
        myPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = myPreferences.edit();
        if(myPreferences.getBoolean("Musica",true)){
            Intent i = new Intent(this, Audio.class);
            i.putExtra("action", Audio.START);
            startService(i);
            cont=1;
            butonmusica.setText("on");

        }
        else{
            butonmusica.setText("off");
            cont=0;
        }


        final Intent i2 = new Intent(this, Audio.class);

    }

    /**
     * Empieza el juego
     */
    public void startGame(View v){
        Intent i=new Intent(this,AndroidLauncher.class);
        startActivity(i);
    }

    /**
     * Si quieres musica o no
     */

    public void preferenciasMusica(View view){
        cont++;
        if(cont%2==0){
            Intent i = new Intent(this, Audio.class);
            i.putExtra("action",Audio.PAUSE);
            stopService(i);
            butonmusica.setText("off");
            myEditor.putBoolean("Musica", false);
            myEditor.commit();

        }
        else{

            Intent i = new Intent(this, Audio.class);
            i.putExtra("action",Audio.START);
            startService(i);
            butonmusica.setText("on");
            myEditor.putBoolean("Musica", true);
            myEditor.commit();
        }

    }

    /**
     * salida del juego
     */

    public void salirJuego(View view){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Salir del juego")
                .setMessage("Deseas salir del juego?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void onPause() {
        super.onPause();
        if(myPreferences.getBoolean("Musica",true)) {
            Intent i = new Intent(this, Audio.class);
            i.putExtra("action", Audio.PAUSE);
            startService(i);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(myPreferences.getBoolean("Musica",true)) {
            Intent i = new Intent(this, Audio.class);
            i.putExtra("action", Audio.START);
            startService(i);
        }
        textscore.setText(""+mb.seleccionar());
    }

}
