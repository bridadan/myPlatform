package com.me.myPlatform;

import com.badlogic.gdx.Game;
import com.me.myPlatform.Assets;

public class myPlatform extends Game {
    @Override
    public void create() {
        Assets.load();
        setScreen(new GameScreen(this));
    }

}
