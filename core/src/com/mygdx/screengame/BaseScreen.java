package com.mygdx.screengame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Alejandro on 14/03/2018.
 */

public class BaseScreen implements Screen {

    private Game juego;

    public BaseScreen(Game j){
        this.juego=j;
    }

    public Game getJuego() {
        return juego;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
