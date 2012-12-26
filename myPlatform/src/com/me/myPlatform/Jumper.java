package com.me.myPlatform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Jumper extends Actor {
    
    private TextureRegion region;
    private Vector2 vel, maxVel, acel, moveAcel, pos, targetPos;
    private boolean movingTowardsPoint;
    private LevelLoader level;
    private GameLevel stage;
    private enum State {MOVING, FALLING, IDLE};
    private State state;

    public Jumper(GameLevel tempStage, float x, float y) {
        state = State.IDLE;
        stage = tempStage;
        region = Assets.jumper;
        acel = new Vector2(0, stage.getGravity());
        vel = new Vector2();
        pos = new Vector2();
        targetPos = new Vector2();
        maxVel = new Vector2(4, 8);
        moveAcel = new Vector2(0.5f, 10);
        movingTowardsPoint = false;
        level = stage.getLevelLoader();
        setSize(32, 32);
        setOrigin(getWidth()/2,0);
        setPosition(x, y);
    }
    
    @Override
    public void act(float delta) {
        float checkX;
        if (level.spaceFree(getX(), getY() + vel.y + acel.y) && level.spaceFree(getX() + getWidth() - 1, getY() + vel.y + acel.y)) {
            vel.y += acel.y;
            state = State.FALLING;
        } else {
            setY((level.getCel(getY() + vel.y + acel.y) + 1) * level.getTileSize());
            vel.y = 0;
            state = State.FALLING;
        }
        
        //Stop accelerating if close enough to target position
        if (movingTowardsPoint) {
            state = State.MOVING;
            if (Math.abs(targetPos.x - getMidCoord().x) <= Math.pow(vel.x, 2)/(2*moveAcel.x)) {
                acel.x = 0;
                movingTowardsPoint = false;
            }            
        }
        
        //Slow down if no touch
        if (!movingTowardsPoint && vel.x != 0) {
            state = State.MOVING;
            acel.x = 0;
            vel.x += (vel.x/Math.abs(vel.x)) * -moveAcel.x;
        }
        
        vel.x += acel.x;
        
        if (vel.x != 0) {
            checkX = getMidCoord().x + (vel.x/Math.abs(vel.x))*(getWidth()/2) + vel.x + acel.x;
            if (level.spaceFree(checkX, getY()) && level.spaceFree(checkX, getY() + getHeight())) {
            } else {
                setX((level.getCel(getMidCoord().x + vel.x + acel.x)) * level.getTileSize());
                vel.x = 0;
            }
        }
        
        //Regulate max velocities;
        if (Math.abs(vel.x) > maxVel.x)
            vel.x = (vel.x/Math.abs(vel.x)) * maxVel.x;
        if (Math.abs(vel.y) > maxVel.y)
            vel.y = (vel.y/Math.abs(vel.y)) * maxVel.y;
        
        //Update position
        setPosition(getX() + vel.x, getY() + vel.y);
        
        //Move camera
        stage.getCamera().position.x = Math.min(Math.max(getMidCoord().x, stage.getCamera().viewportWidth/2), stage.getLevelWidth() - stage.getCamera().viewportWidth/2);
        stage.getCamera().position.y = Math.min(Math.max(getMidCoord().y, getStage().getCamera().viewportHeight/2), stage.getLevelHeight() - stage.getCamera().viewportHeight/2);
    }
    
    public void moveTowards(Vector2 touchPos) {
        int dir;
        if (Math.abs(touchPos.x - getMidCoord().x) > 10) {
            targetPos.set(touchPos);
            movingTowardsPoint = true;
            dir = (int) ((targetPos.x - getMidCoord().x)/Math.abs((targetPos.x - getMidCoord().x)));
            acel.x = dir*moveAcel.x; 
        }
    }
    
    @Override
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
        pos.set(x, y);
    }
    
    public void setmovingTowardsPoint(boolean a) {
        movingTowardsPoint = a;
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
    public Vector2 getMidCoord() {
        return new Vector2(getX()+ getWidth()/2, getY()+ getHeight()/2);
    }
    
}
