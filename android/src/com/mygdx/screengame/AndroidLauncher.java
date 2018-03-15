package com.mygdx.screengame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import Util.Musica;

public class AndroidLauncher extends AndroidApplication implements Musica{
	private SharedPreferences p;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		p= PreferenceManager.getDefaultSharedPreferences(this);
		initialize((new MyGdxGame(this,new Basededatoandroid(this))),config);
	}

	@Override
	public void Menu() {
		Intent i = new Intent (this,Menu.class);
		startActivity(i);
	}
	/**
	 * Musica activada
	 */
	@Override
	public void on() {
		if(p.getBoolean("Musica",true)) {
			Intent i = new Intent(this, Audio.class);
			i.putExtra("action", Audio.START);
			startService(i);
		}
	}
	/**
	 * Musica desactivada
	 */
	@Override
	public void off() {
		if(p.getBoolean("Musica",true)) {
			Intent i = new Intent(this, Audio.class);
			i.putExtra("action", Audio.PAUSE);
			startService(i);
		}
	}
}
