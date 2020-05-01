package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.box2d.game.ces.components.AnimationComponent;
import com.box2d.game.ces.components.StateComponent;
import com.box2d.game.ces.components.TextureComponent;
import com.box2d.game.ces.components.TransformComponent;

public class AnimationSystem extends IteratingSystem {

    ComponentMapper<TextureComponent> textureCompMapper;
    ComponentMapper<AnimationComponent> animationCompMapper;
    ComponentMapper<StateComponent> stateCompMapper;

    @SuppressWarnings("unchecked")
    public AnimationSystem() {
        super(Family.all(TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class).get());

        textureCompMapper = ComponentMapper.getFor(TextureComponent.class);
        animationCompMapper = ComponentMapper.getFor(AnimationComponent.class);
        stateCompMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = animationCompMapper.get(entity);
        StateComponent state = stateCompMapper.get(entity);

        if(animationComponent.animations.containsKey(state.get())){
            TextureComponent textureComp = textureCompMapper.get(entity);
            textureComp.region = (TextureRegion) animationComponent
                    .animations
                    .get(state.get())
                    .getKeyFrame(state.time, state.isLooping);
        }
        state.time += deltaTime;
    }
}
