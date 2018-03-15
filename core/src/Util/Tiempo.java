package Util;

import com.mygdx.screengame.Pantalla;

import java.awt.DisplayMode;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alejandro on 15/03/2018.
 */

public class Tiempo {

    private Timer timer = new Timer();
    private int segundos=0;

    /**
     * El juego durará 30 segundos
     */

    class ContadorCubo extends TimerTask{
        public void run() {
            segundos++;
            if(getSegundos()==30){
                Detener();
                Pantalla.acabaTiempo();



            }
        }
    }



    public void Contar()
    {
        this.segundos=0;
        timer = new Timer();
        timer.schedule(new ContadorCubo(), 0, 1000);
    }

    public void Detener() {
        timer.cancel();
    }

    public int getSegundos()
    {
        return this.segundos;
    }
}
