package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.box2d.game.Box2dTutorial;

public class MenuScreen implements Screen {
    private Box2dTutorial parent;
    private Stage stage;

    public MenuScreen(Box2dTutorial parent){
        this.parent = parent;
        this.stage = new Stage(new ScreenViewport());

        // any input will send a request from stage for response
        Gdx.input.setInputProcessor(stage);

        // act asks our stage to perform as we "draw" the curtains
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
                    // ^ maximum frame rate with this MathMin function

        
        stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
