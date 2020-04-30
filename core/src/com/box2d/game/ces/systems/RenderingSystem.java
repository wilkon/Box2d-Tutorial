package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    static final float PPM = 32.0f; // pixels per meter of box2d objects contain

    //width/height calculated against our PPM
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    //ratio for converting pixels to meters
    public static final float PIXELS_TO_METERS = 1.0f / PPM;

    public RenderingSystem(Family family, Comparator<Entity> comparator) {
        super(family, comparator);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
