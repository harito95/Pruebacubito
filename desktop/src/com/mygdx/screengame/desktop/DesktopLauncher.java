package com.mygdx.screengame.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.screengame.MyGdxGame;

import Util.Musica;
import jdk.internal.org.objectweb.asm.ClassReader;

public class DesktopLauncher{
	public void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("Logos/gamelogosmall.png", Files.FileType.Local);
		config.title="Recoge gotas";
		new LwjglApplication(new MyGdxGame(null,null), config);
	}

}
