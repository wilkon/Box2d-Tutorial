package com.box2d.game.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.Box2dContactListener;
import com.box2d.game.controllers.KeyboardController;
import com.box2d.game.factories.BodyFactory;
import com.box2d.game.loaders.B2dAssetLoader;

import static com.box2d.game.factories.BodyFactory.*;

public class Box2dModel {
    public World world;

    private Body bodyd, bodys, bodyk;
    public Body player;
    private KeyboardController controller;
    private OrthographicCamera camera;
    private B2dAssetLoader assMan;

    public boolean isSwimming = false;
    public static final int BOING_SOUND = 0;
    public static final int PING_SOUND = 1;

    public Box2dModel(KeyboardController controller, OrthographicCamera camera, B2dAssetLoader assMan){
        this.assMan = assMan;
        this.controller = controller;
        this.camera = camera;
        this.world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new Box2dContactListener());
        createFloor();
//        createObject();
//        createMovingObject();

        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        player = bodyFactory.makeBoxPolyBody(1, 1,
                2, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody, false);

        Body water = bodyFactory.makeBoxPolyBody(1, -8,
                40, 20, RUBBER, BodyDef.BodyType.StaticBody);
        water.setUserData("IAMTHESEA");

        bodyFactory.makeAllFixturesSensors(water);

        assMan.queueAddSounds();
        assMan.manager.finishLoading();
    }

    public void logicStep(float delta){
        if(controller.left){
            player.applyForceToCenter(-10, 0, true);
        }else if(controller.right){
            player.applyForceToCenter(10, 0, true);
        }else if(controller.up){
            player.applyForceToCenter(0, 10, true);
        }else if(controller.down){
            player.applyForceToCenter(0, -10, true);
        }

        if(controller.isMouse1Down && pointIntersectsBody(player, controller.mouseLocation)){
            System.out.println("that hurts!");
        }

        if(isSwimming){
            player.applyForceToCenter(0, 40, true);
        }
        world.step(delta, 3, 3);
    }

    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation){
        Vector3 mousePosition = new Vector3(mouseLocation, 0);

        camera.unproject(mousePosition);
        if(body.getFixtureList().first().testPoint(mousePosition.x, mousePosition.y)){
            return true;
        }
        return false;
    }

    public void playSound(int sound){
        switch(sound){
            case BOING_SOUND:
                assMan.boingSound.play();
                break;
            case PING_SOUND:
                assMan.pingSound.play();
                break;
        }
    }

    private void createObject(){

        // Dynamic Bodies - affected by gravity and other bodies.
        // used for player/enemies
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        bodyd = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        //align our body with a physical object
        bodyd.createFixture(shape, 0.0f);

        shape.dispose();
    }

    private void createFloor(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);

        bodys = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);

        bodys.createFixture(shape, 0.0f);
        shape.dispose();
    }

    private void createMovingObject(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, -12);

        bodyk = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        bodyk.createFixture(shape, 0.0f);

        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }
}
