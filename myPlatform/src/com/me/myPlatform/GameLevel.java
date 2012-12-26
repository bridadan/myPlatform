package com.me.myPlatform;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameLevel extends Stage {

    private Jumper jumper;
    private static int LEVEL_WIDTH;
    private static int LEVEL_HEIGHT;
    private float gravity;
    private Vector2 touchpos;
    private LevelLoader level;
    private OrthographicCamera camera;
    
    public GameLevel(FileHandle levelFile) {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        setCamera(camera);
        try {
            level = new LevelLoader(this, levelFile);
            level.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Gdx.app.log("EXCEPTION", "Something went wrong reading the level file.");
            e.printStackTrace();
        }
        LEVEL_WIDTH = (int) level.getWidth() * level.getTileSize();
        LEVEL_HEIGHT = (int) level.getHeight() * level.getTileSize();
        addActor(level);
        gravity = -0.5f;
        jumper = new Jumper(this, 180, 480);
        addActor(jumper);
        touchpos = new Vector2();
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchpos.set(screenX, screenY);
        jumper.moveTowards(screenToStageCoordinates(touchpos));
        return false; 
    }
    
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchpos.set(screenX, screenY);
        jumper.moveTowards(screenToStageCoordinates(touchpos));
        return false; 
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        jumper.setmovingTowardsPoint(false);
        return false; 
    }
    
    public int getLevelWidth() {
        return LEVEL_WIDTH;
    }
    
    public int getLevelHeight() {
        return LEVEL_HEIGHT;
    }
    
    public float getGravity() {
        return gravity;
    }
    
    public LevelLoader getLevelLoader() {
        return level;
    }
}
