package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.box2d.game.Box2dTutorial;

import static com.box2d.game.constants.GameViews.*;

public class LoadingScreen implements Screen {
    private Box2dTutorial parent;

    TextureAtlas atlas;
    TextureAtlas.AtlasRegion title, dash;

    SpriteBatch batch;

    int currentLoadingStage = 0;
    public static final int IMAGE=0, FONT=1, PARTY=2, SOUND=3, MUSIC=4;

    public LoadingScreen(Box2dTutorial parent){
        this.parent = parent;
        batch = new SpriteBatch();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    }

    @Override
    public void show() {
        parent.assMan.queueAddLoadingImages();
        parent.assMan.manager.finishLoading();

        atlas = parent.assMan.manager.get("images/loading.atlas");
        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");

        parent.assMan.queueAddImages();
        System.out.println("Loading images...");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(title, 135, 250);
        batch.end();

        if(parent.assMan.manager.update()){
            currentLoadingStage += 1;
            switch(currentLoadingStage){
                case FONT:
                    System.out.println("Loading fonts...");
                    parent.assMan.queueAddFonts();
                    break;
                case PARTY:
                    System.out.println("loading particles...");
                    parent.assMan.queueAddParticleEffects();
                    break;
                case SOUND:
                    System.out.println("Loading sounds...");
                    parent.assMan.queueAddSounds();
                    break;
                case MUSIC:
                    System.out.println("loading music...");
                    parent.assMan.queueAddMusic();
                    break;
                case 5:
                    System.out.println("finished");
                    break;
            }
            if(currentLoadingStage > 5){
                countdown -= delta;
                currentLoadingStage = 5;
                if(countdown < 0) {
                    parent.switchScreen(MENU);
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
