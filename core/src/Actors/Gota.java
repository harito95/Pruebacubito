package Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygdx.screengame.Pantalla;

import java.util.Random;

import Constante.Constante;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class Gota extends Actor {
    protected Sprite sprite;
    private Random r = new Random();
    private float aleatorioX;
    private boolean colision;
    private float time=0;
    private MoveToAction mv = new MoveToAction();


    public Gota(Texture t, float Gotatiempo ) {

        sprite=new Sprite(t);
        colision=false;
        aleatorioX= r.nextFloat()*(0+ Constante.Ancho);
        setPosition(aleatorioX,(Constante.Altura+(Constante.Altura/2)));
        mv.setPosition(aleatorioX,-200);
        mv.setDuration(Gotatiempo);
        setName("Gota");
        this.addAction(mv);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setRotation(0);
        sprite.setPosition(getX(),getY());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(-200==getY()) {

            cambiarPosicion();
        }
    }

    /**
     * Colision entre el cubito y la gotas de agua
     */

    public void checkCollision(Cubo n) {
        boolean overlaps = getHitBox().overlaps(n.getHitBox());
        if (overlaps && colision == false) {
            colision = true;
            cambiarPosicion();

        } else if (!overlaps) {
            colision = false;
        }
    }

    /**
     * cambiarPosicion
     */

    public void cambiarPosicion(){

        if (colision){
            mv.reset();
            aleatorioX= r.nextFloat()*(0+Constante.Ancho);
            sprite.setPosition(aleatorioX,Constante.Altura);
            setPosition(aleatorioX,Constante.Altura);
            colision=false;
            mv.setPosition(aleatorioX,-200);
            time= (float) (Math.random() * (3.0f + 7.0f));
            mv.setDuration(time);
            this.addAction(mv);
            Pantalla.puntuacion+=5;

        }
        if(!Constante.Modificar) {
            Constante.Modificar=true;
            aleatorioX= r.nextFloat()*(0+Constante.Ancho);
            sprite.setPosition(aleatorioX,Constante.Altura);
            setPosition(aleatorioX,Constante.Altura);

            //elementos del tiempo
            time= (float) (Math.random() * (3.0f + 7.0f));
            mv.reset();
            mv.setPosition(aleatorioX,-200);
            mv.setDuration(time);
            this.addAction(mv);
            Constante.Modificar=false;

        }}

    public com.badlogic.gdx.math.Rectangle getHitBox(){

        return sprite.getBoundingRectangle();

    }

}
