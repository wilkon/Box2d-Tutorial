package com.box2d.game.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Box2dModel {
    public World world;

    public Box2dModel(){
        this.world = new World(new Vector2(0, -10f), true);
    }

    public void logicStep(float delta){
        world.step(delta, 3, 3);
    }
}
