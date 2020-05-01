package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.box2d.game.ces.components.CollisionComponent;
import com.box2d.game.ces.components.PlayerComponent;
import com.box2d.game.ces.components.TypeComponent;

public class CollisionSystem extends IteratingSystem {
    ComponentMapper<CollisionComponent> collisionCompMapper;
    ComponentMapper<PlayerComponent> playerCompMapper;

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, PlayerComponent.class).get());

        collisionCompMapper = ComponentMapper.getFor(CollisionComponent.class);
        playerCompMapper = ComponentMapper.getFor(PlayerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent cc = collisionCompMapper.get(entity);

        Entity collidedEntity = cc.collisionEntity;
        if(collidedEntity != null){
            TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
            if(type != null){
                switch(type.type){
                    case TypeComponent.ENEMY:
                        System.out.println("player hit enemy");
                        break;
                    case TypeComponent.SCENERY:
                        System.out.println("player hit scenery");
                        break;
                    case TypeComponent.OTHER:
                        System.out.println("player hit other type");
                        break;
                }
                //collision handled - reset component
                cc.collisionEntity = null;
            }
        }
    }
}
