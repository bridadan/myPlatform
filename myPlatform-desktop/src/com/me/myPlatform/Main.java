package com.me.myPlatform;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "myPlatform";
		cfg.useGL20 = false;
		cfg.width = 640;
		cfg.height = 480;
		
		new LwjglApplication(new myPlatform(), cfg);
	}
}
