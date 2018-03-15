package Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import Constante.Constante;


/**
 * Created by Alejandro on 04/03/2018.
 */

public class CubitoKeyBoardListener implements InputProcessor {
    Cubo cubito;

    /**
     * Se ocupa del movimiento del cubo
     */

    public CubitoKeyBoardListener(Cubo c){
        this.cubito=c;
    }


    @Override
    public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.RIGHT:
                    cubito.moving.add("right");
                    break;
                case Input.Keys.LEFT:
                    cubito.moving.add("left");
                    break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.RIGHT:
                cubito.moving.remove("right");
                break;
            case Input.Keys.LEFT:
                cubito.moving.remove("left");
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(Constante.Ancho/2< screenX){
            cubito.moving.add("right");
        }
        if(Constante.Ancho/2> screenX){
            cubito.moving.add("left");
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(Constante.Ancho/2< screenX){
            cubito.moving.remove("right");
        }
        if(Constante.Ancho/2> screenX){
            cubito.moving.remove("left");
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
