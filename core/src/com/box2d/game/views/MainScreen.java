package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.Box2dTutorial;
import com.box2d.game.controllers.KeyboardController;
import com.box2d.game.models.Box2dModel;

public class MainScreen implements Screen {
    private Box2dTutorial parent;
    private World world;

    Box2dModel model;
    KeyboardController controller;
    OrthographicCamera camera;

    Box2DDebugRenderer debugRenderer;

    public MainScreen(Box2dTutorial parent){
        this.parent = parent;
        controller = new KeyboardController();
        camera = new OrthographicCamera(32, 24);
        model = new Box2dModel(controller, camera);
        debugRenderer = new Box2DDebugRenderer(true, true, true,
                true,true, true);
        this.world = model.world;
    }

    @Override
    public void render(float delta) {
        //we will use this to move our game logic
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
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
