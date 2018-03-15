package com.mygdx.screengame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import Util.Basedatos;
import Util.Musica;

public class MyGdxGame extends Game {
	private Screen currentScreen;
	public Pantalla g;
	public Musica music;
	public Basedatos mb;



	public MyGdxGame(Musica music, Basedatos bd) {
		super();
		this.music = music;
		this.mb=bd;
	}




	@Override
	public void create() {

		g = new Pantalla(this);
		currentScreen = g;
		setScreen(currentScreen);

	}

	public Basedatos getMb() {
		return mb;
	}


}
