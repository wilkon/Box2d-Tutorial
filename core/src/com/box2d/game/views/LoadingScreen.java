package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.box2d.game.Box2dTutorial;

import static com.box2d.game.constants.GameViews.*;

public class LoadingScreen implements Screen {
    private Box2dTutorial parent;

    TextureAtlas atlas;
    TextureAtlas.AtlasRegion title, dash, copyright, background;
    Animation flameAnimation;
    private Stage stage;

    Image titleImage, copyrightImage;
    Table table;
    Table loadingTable;

    SpriteBatch batch;

    int currentLoadingStage = 0;
    float countdown = 5f, stateTime=0f;
    public static final int IMAGE=0, FONT=1, PARTY=2, SOUND=3, MUSIC=4;

    public LoadingScreen(Box2dTutorial parent){
        this.parent = parent;
        stage = new Stage(new ScreenViewport());

        parent.assMan.queueAddLoadingImages();
        parent.assMan.manager.finishLoading();
        atlas = parent.assMan.manager.get("images/loading.atlas", TextureAtlas.class);

        loadAssets();
        parent.assMan.queueAddImages();
        System.out.println("loading images");
    }

    @Override
    public void show() {
        titleImage = new Image(title);
        copyrightImage = new Image(copyright);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);

        loadingTable = new Table();
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));
        loadingTable.add(new LoadingBarPart(dash, flameAnimation));

        table.add(titleImage).align(Align.center).pad(10, 0, 0, 0).colspan(10);
        table.row();
        table.add(loadingTable).width(400);
        table.row();
        table.add(copyrightImage).align(Align.center).pad(10, 0, 0, 0).colspan(0);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(parent.assMan.manager.update()){
            currentLoadingStage += 1;
            if(currentLoadingStage <= 5){
                loadingTable.getCells().get((currentLoadingStage-1)*2).getActor().setVisible(true);
                loadingTable.getCells().get((currentLoadingStage-1)*2+1).getActor().setVisible(true);
            }
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
        stage.act();
        stage.draw();
    }

    private void loadAssets(){
        background = atlas.findRegion("flamebackground");
        copyright = atlas.findRegion("copyright");

        parent.assMan.queueAddLoadingImages();
        parent.assMan.manager.finishLoading();

        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");
        flameAnimation = new Animation(0.07f, atlas.findRegions("flames/flames"),
                Animation.PlayMode.LOOP);
    }

    private void drawLoadingBar(int stage, TextureRegion currentFrame){
        for(int i=0; i<stage; i++){
            batch.draw(currentFrame, 50 + (i * 50), 150,
                    50, 50);
            batch.draw(currentFrame, 35 + (i*50), 140,
                    80, 80);
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
