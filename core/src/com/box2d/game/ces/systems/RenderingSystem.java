package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {
    public RenderingSystem(Family family, Comparator<Entity> comparator) {
        super(family, comparator);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
