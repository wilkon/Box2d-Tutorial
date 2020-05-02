package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.box2d.game.ces.components.B2dBodyComponent;
import com.box2d.game.ces.components.PlayerComponent;
import com.box2d.game.ces.components.StateComponent;
import com.box2d.game.controllers.KeyboardController;

public class PlayerControlSystem extends IteratingSystem {

    ComponentMapper<PlayerComponent> playerCompMapper;
    ComponentMapper<B2dBodyComponent> bodyCompMapper;
    ComponentMapper<StateComponent> stateCompMapper;
    KeyboardController controller;

    public PlayerControlSystem(KeyboardController keyboardController) {
        super(Family.all(PlayerComponent.class).get());
        this.controller =  keyboardController;
        playerCompMapper = ComponentMapper.getFor(PlayerComponent.class);
        bodyCompMapper = ComponentMapper.getFor(B2dBodyComponent.class);
        stateCompMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        B2dBodyComponent b2body = bodyCompMapper.get(entity);
        StateComponent state = stateCompMapper.get(entity);

        if(b2body.body.getLinearVelocity().y > 0){
            state.set(StateComponent.STATE_FALLING);
        }

        if(b2body.body.getLinearVelocity().y == 0){
            if(state.get() == StateComponent.STATE_FALLING){
                state.set(StateComponent.STATE_NORMAL);
            }
            if(b2body.body.getLinearVelocity().x != 0){
                state.set(StateComponent.STATE_MOVING);
            }
        }

        if(controller.left){
            b2body.body.setLinearVelocity(
                    MathUtils.lerp(b2body.body.getLinearVelocity().x,-5f, 0.2f),
                    b2body.body.getLinearVelocity().y);
        }
        if(controller.right){
            b2body.body.setLinearVelocity(
                    MathUtils.lerp(b2body.body.getLinearVelocity().x, 5f, .2f),
                    b2body.body.getLinearVelocity().y);
        }
        if(!controller.left || !controller.right){
            b2body.body.setLinearVelocity(
                    MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 0.1f),
                    b2body.body.getLinearVelocity().y);
        }

        boolean isNormalOrMoving = (state.get() == StateComponent.STATE_NORMAL ||
                state.get() == StateComponent.STATE_MOVING);
        if(controller.up && isNormalOrMoving){
            //b2body.body.applyForceTocenter(0, 3000, true);
            b2body.body.applyLinearImpulse(
                    0, 12f,
                    b2body.body.getWorldCenter().x,
                    b2body.body.getWorldCenter().y,
                    true);
            state.set(StateComponent.STATE_JUMPING);
        }
    }
}
