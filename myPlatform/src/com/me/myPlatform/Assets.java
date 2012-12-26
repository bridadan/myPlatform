package com.me.myPlatform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture items;
    public static final int NUM_TILES = 1;
    public static TextureRegion jumper;
    public static TextureRegion tiles[] = new TextureRegion[NUM_TILES];
    public static FileHandle level1, level2;

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load () {
        items = loadTexture("data/items.png");
        jumper = new TextureRegion(items, 0, 0, 32, 32);
        tiles[0] = new TextureRegion(items, 32, 0, 32, 32);
        level1 = Gdx.files.internal("data/level1.txt");
        level2 = Gdx.files.internal("data/level2.txt");
    }

    /*public static void playSound (Sound sound) {
        if (Settings.soundEnabled) sound.play(1);
    }*/
}
