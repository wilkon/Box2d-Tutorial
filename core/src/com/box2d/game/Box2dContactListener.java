package com.box2d.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.models.Box2dModel;

public class Box2dContactListener implements ContactListener {

    private Box2dModel parent;

    Box2dModel game;

    public Box2dContactListener(Box2dModel game){
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact!!!!");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            System.out.println("Using The Force.");
            fb.getBody().applyForceToCenter(new Vector2(-100000, - 100000), true);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
