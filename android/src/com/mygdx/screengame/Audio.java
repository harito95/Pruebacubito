package com.mygdx.screengame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class Audio extends Service{

    Boolean shouldPause = false;
    public static final int DECREASE = 1;
    public static final int INCREASE = 2;
    public static final int START = 3;
    public static final int PAUSE = 4;
    MediaPlayer loop;

    /**
     * Comienza la musica
     */

    private void Comienzo(){
        if(loop == null){
            loop = MediaPlayer.create(this, R.raw.rock);
        }
        if(!loop.isPlaying()){
            loop.setLooping(true);
            loop.start();
        }
    }

    private void decrease(){
        loop.setVolume(0.2f, 0.2f);
    }

    private void increase(){
        loop.setVolume(1.0f, 1.0f);
    }

    private void start(){
        Comienzo();
        shouldPause = false;
    }

    private void pause(){
        shouldPause = true;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(shouldPause) {
                            loop.pause();
                        }
                    }
                }, 100);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "Creating service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(getClass().getSimpleName(), "Intent received");

        try {
            int actionDefault = 0;
            int action = actionDefault;
            if(intent != null){
                if(intent.hasExtra("action")){
                    action = intent.getIntExtra("action", actionDefault);
                }
            }
            switch (action) {
                case INCREASE:
                    increase();
                    break;
                case DECREASE:
                    decrease();
                    break;
                case START:
                    start();
                    break;
                case PAUSE:
                    pause();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loop != null) loop.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
