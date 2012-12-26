package com.me.myPlatform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Block extends Actor{
    //DON'T USE THIS CLASS
    private TextureRegion region;
    
    public Block() {
        setSize(32, 32);
        //region = Assets.block;
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
