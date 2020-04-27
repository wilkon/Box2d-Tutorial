package com.box2d.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.box2d.game.Box2dTutorial;

import static com.box2d.game.constants.GameViews.*;

public class LoadingScreen implements Screen {
    private Box2dTutorial parent;

    public LoadingScreen(Box2dTutorial parent){
        this.parent = parent;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.switchScreen(MENU);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
