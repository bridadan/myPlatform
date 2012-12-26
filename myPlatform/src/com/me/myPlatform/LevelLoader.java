package com.me.myPlatform;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LevelLoader extends Actor{
    private static final int TILE_SIZE = 32;
    private BufferedReader reader;
    private String in;
    private String[] strArray;
    private int[][] obstacles;
    private GameLevel level;
    private Block block;
    
    public LevelLoader(GameLevel superLevel, FileHandle file) throws IOException {
        level = superLevel;
        reader = file.reader(100);
        in = reader.readLine();
        strArray = in.split(" ");
        setWidth(Integer.parseInt(strArray[0]));
        setHeight(Integer.parseInt(strArray[1]));
        obstacles = new int [(int) getHeight()][(int) getWidth()];
    }
    
    public void load() throws IOException {
        for (int i = (int) getHeight() - 1; i >= 0; i--) {
            in = reader.readLine();
            strArray = in.split(" ");
            for (int j = 0; j < getWidth(); j++) {
                obstacles[i][j] = Integer.parseInt(strArray[j]);
                /*block = new Block();
                block.setPosition(j*block.getWidth(), (height - i - 1)*block.getHeight());
                level.addActor(block);*/
            }
        }
    }
    
    public int getCel(float x) {
        return ((int) x) / TILE_SIZE;
    }
    
    public boolean spaceFree(float x, float y) {
        int row = getCel(y);
        int col = getCel(x);
        
        if (obstacles[row][col] == 0) {
            return true;
        } else {
            return false;
        }
            
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        for (int i = (int) getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < getWidth(); j++) {
                if (obstacles[i][j] != 0) {
                    batch.draw(Assets.tiles[obstacles[i][j] - 1], j*TILE_SIZE, i*TILE_SIZE, 0, 0, TILE_SIZE, TILE_SIZE, 1, 1, 0);
                }
            }
        }
    }
    
    public int getTileSize() {
        return TILE_SIZE;
    }
}
