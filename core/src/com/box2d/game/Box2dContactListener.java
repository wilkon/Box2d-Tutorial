package com.box2d.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.box2d.game.models.Box2dModel;

public class Box2dContactListener implements ContactListener {

    private Box2dModel parent;

    public Box2dContactListener(Box2dModel parent){
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact!!!!");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        if (fa.getBody().getUserData() == "IAMTHESEA") {
            parent.isSwimming = true;
            return;
        } else if (fb.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = true;
            return;
        }

        if(fa.getBody().getType() == BodyType.StaticBody){
            this.trampoline(fa, fb);
        }else if(fb.getBody().getType() == BodyType.StaticBody){
            this.trampoline(fb, fa);
        }
    }

    private void trampoline(Fixture staticFixture, Fixture otherFixture){
        System.out.println("force up");
        otherFixture.getBody().applyForceToCenter(new Vector2(-1000, -1000), true);
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("Contact!");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = false;
            return;
        }else if(fb.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = false;
            return;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
