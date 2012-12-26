package com.me.myPlatform;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen{
    private GameLevel level;
    private Game game;
    
    public GameScreen(Game superGame) {
        game = superGame;
        level = new GameLevel(Assets.level2);
        Gdx.input.setInputProcessor(level);     
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        level.act();
        level.draw();
    }

    @Override
    public void resize(int width, int height) {
        level.setViewport(640, 480, true); 
        level.getCamera().translate(-level.getGutterWidth(), -level.getGutterHeight(), 0);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        level.dispose();
    }
    

}

