package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.box2d.game.ces.components.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> transformComponentMapper;

    public ZComparator(){
        transformComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity A, Entity B) {
        float az = transformComponentMapper.get(A).position.z;
        float bz = transformComponentMapper.get(B).position.z;
        if(az == bz) return 0;
        return az > bz ? 1 : -1;
    }
}
