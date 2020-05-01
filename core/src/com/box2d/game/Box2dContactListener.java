package com.box2d.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.ces.components.CollisionComponent;
import com.box2d.game.models.Box2dModel;

public class Box2dContactListener implements ContactListener {

    private Box2dModel parent;

    public Box2dContactListener(){
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact!!!!");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        if (fa.getBody().getUserData() instanceof Entity) {
            Entity ent = (Entity) fa.getBody().getUserData();
            entityCollision(ent, fb);
            return;
        } else if (fb.getBody().getUserData() instanceof Entity){
            Entity ent = (Entity) fb.getBody().getUserData();
            entityCollision(ent, fa);
            return;
        }
    }

    private void entityCollision(Entity ent, Fixture fb){
        if(fb.getBody().getUserData() instanceof Entity){
            Entity fbEntity = (Entity) fb.getBody().getUserData();

            CollisionComponent entityCol = ent.getComponent(CollisionComponent.class);
            CollisionComponent fbCol = fbEntity.getComponent(CollisionComponent.class);

            if(entityCol != null){
                entityCol.collisionEntity = fbEntity;
            }else if(fbCol != null){
                fbCol.collisionEntity = ent;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("contact end");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
