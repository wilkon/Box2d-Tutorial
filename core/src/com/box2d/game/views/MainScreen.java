package com.box2d.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.Box2dContactListener;
import com.box2d.game.Box2dTutorial;
import com.box2d.game.ces.components.*;
import com.box2d.game.ces.systems.*;
import com.box2d.game.controllers.KeyboardController;
import com.box2d.game.factories.BodyFactory;
import com.box2d.game.models.Box2dModel;

public class MainScreen implements Screen {
    private Box2dTutorial parent;
    private World world;
    private BodyFactory bodyFactory;
    private PooledEngine engine;

    Box2dModel model;
    KeyboardController controller;
    OrthographicCamera camera;

    Box2DDebugRenderer debugRenderer;

    public TextureAtlas.AtlasRegion playerTexture;
    private Sound ping;
    private Sound boing;

    private SpriteBatch batch;
    private TextureAtlas atlas;

    public MainScreen(Box2dTutorial parent){
        this.parent = parent;
        controller = new KeyboardController();
        engine = new PooledEngine();
        this.world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new Box2dContactListener());
        bodyFactory = BodyFactory.getInstance(world);

        parent.assMan.queueAddSounds();
        parent.assMan.manager.finishLoading();
        atlas = parent.assMan.manager.get("images/game.atlas", TextureAtlas.class);
//        ping = parent.assMan.manager.get("sounds/ping.wav", Sound.class);
//        boing = parent.assMan.manager.get("sounds/boing.wav", Sound.class);

        batch = new SpriteBatch();

        RenderingSystem renderingSystem = new RenderingSystem(batch);
        camera = renderingSystem.getCamera();
        batch.setProjectionMatrix(camera.combined);

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new PhysicsDebugRenderer(world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
    }

    @Override
    public void render(float delta) {
        //we will use this to move our game logic
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
    }

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
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
