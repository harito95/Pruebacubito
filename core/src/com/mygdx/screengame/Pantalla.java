package com.mygdx.screengame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.ArrayList;
import java.util.Random;

import Actors.CubitoKeyBoardListener;
import Actors.Cubo;
import Actors.Gota;
import Constante.Constante;
import Util.Tiempo;

/**
 * Created by Alejandro on 01/03/2018.
 */

public class Pantalla extends BaseScreen{

    public static ArrayList<Gota> gotita;
    public static int puntuacion;
    private Stage stage;
    private Group players;
    private Texture txtcubito;
    private Texture txtGotita;
    private Texture background;
    private float tiempogota;
    private String yourScoreName;
    private InputMultiplexer multiplexer;
    private Random random;
    private BitmapFont yourBitmapFontName;
    private static MyGdxGame mg;
    private Tiempo t;

    public Pantalla(final MyGdxGame g) {
        super(g);
        this.mg = g;
        t=new Tiempo();
        t.Contar();
        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        gotita = new ArrayList<Gota>();
        puntuacion=0;
        yourScoreName ="score"+puntuacion;
        yourBitmapFontName = new BitmapFont();
        random = new Random();
        g.music.on();


        /**
        *Texturas de las imagenes
         */

        txtcubito = new Texture(Gdx.files.internal("Actors/bucket.png"));
        txtGotita = new Texture(Gdx.files.internal("Actors/droplet.png"));
        players = new Group();
        players.addActor(new Cubo(txtcubito));
        crearGota();
        stage.addActor(players);
        background = new Texture(Gdx.files.internal("Backgrounds/beach.jpg"));

        /**
         * Movimiento del cubito
         */
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new CubitoKeyBoardListener((Cubo) players.getChildren().get(0)));
        Gdx.input.setInputProcessor(multiplexer);
    }

    /**
     * Musica funcionando
     */

    @Override
    public void show() {

        super.show();
        mg.music.on();

    }



    @Override
    public void resize(int width, int height) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, width, height);
        stage.getBatch().end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        txtcubito.dispose();
        txtGotita.dispose();
        yourBitmapFontName.dispose();

    }

    /**
     *Crear las gotas de agua
     */

    public void crearGota() {

        for (int i = 0; i < Constante.Gotas; i++) {

            tiempogota = random.nextFloat() * (8+ 12);
            Gota g = new Gota(txtGotita, tiempogota);
            gotita.add(g);
            players.addActor(g);
        }
    }


    /**
     * Comprobacion de las colisiones
     */

    public void colisiones() {
        for (int i = 1; i < gotita.size(); i++) {

            ((Gota) players.getChildren().get(i)).checkCollision(((Cubo) players.getChildren().get(0)));

        }

    }

    public static void acabaTiempo(){
        int puntiacionAntigua= mg.getMb().seleccionar();
        if(puntuacion> puntiacionAntigua) {

            mg.getMb().Guardar(puntuacion);
            mg.getMb().seleccionar();
        }
        mg.dispose();
        Gdx.app.exit();
    }

    /**
     * Renderizamos el bitMap, stage y llamamos a colisiones
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().setColor(stage.getBatch().getColor().r, stage.getBatch().getColor().g, stage.getBatch().getColor().b, 1f);
        stage.getBatch().begin();
        yourBitmapFontName.setColor(Color.GREEN);
        yourBitmapFontName.getData().setScale(3f);
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        yourScoreName="Score:"+puntuacion;
        yourBitmapFontName.draw(stage.getBatch(), yourScoreName, Constante.Ancho/2,Constante.Altura-50);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
        colisiones();

    }


    public Group getGroup() {
        return players;
    }

    public InputMultiplexer getInputMultiplexer() {
        return multiplexer;
    }


    @Override
    public void resume() {
        super.resume();
        mg.music.on();
    }


    @Override
    public void pause() {
        super.pause();
        mg.music.off();
    }
}
