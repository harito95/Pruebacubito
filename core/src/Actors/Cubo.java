package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.HashSet;
import java.util.Set;

import Constante.Constante;

/**
 * Created by Alejandro on 14/03/2018.
 */

public class Cubo extends Actor {

    protected Sprite sprite;
    protected Set moving;


    public Cubo(Texture t){
        sprite=new Sprite(t);
        sprite.setBounds(0, Constante.Altura/2,100,100);
        setBounds(Constante.Altura/2,0,100,100);
        setName("cubo");
        moving= new HashSet();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setScale(getScaleX(),getScaleY());
        sprite.setRotation(getRotation());
        sprite.setPosition(getX(),getY());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
    }


    /**
     * Mueve a la izquierda
     */

    public void moveLeft(float delta) {

        Gdx.app.log("Juego","Estoy en metodo left");
        if(getX()<= 0){

        }else {

            MoveByAction moveLeftAction = new MoveByAction();
            moveLeftAction.setAmount(-10, 0);
            moveLeftAction.setDuration(1*delta);
            addAction(moveLeftAction);
        }
    }

    /**
     * Mueve a la derecha
     */

    public void moveRight(float delta) {
        Gdx.app.log("Juego","Estoy en metodo Rigth");
        if(getX()>= Gdx.graphics.getWidth()){

        }else {

            MoveByAction moveRightAction = new MoveByAction();
            moveRightAction.setAmount(10, 0);
            moveRightAction.setDuration(1*delta);
            addAction(moveRightAction);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(moving.contains("right")){
            moveRight(delta);
        }
        if(moving.contains("left")){
            moveLeft(delta);
        }
    }


    public Sprite getSprite(){
        return sprite;
    }

    public com.badlogic.gdx.math.Rectangle getHitBox(){
        return sprite.getBoundingRectangle();

    }

}
