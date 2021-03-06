package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.box2d.game.Box2dTutorial;

import static com.box2d.game.constants.GameViews.*;

public class MenuScreen implements Screen {
    private Box2dTutorial parent;
    private Stage stage;
    private Skin skin;

    public MenuScreen(Box2dTutorial parent){
        this.parent = parent;
        this.stage = new Stage(new ScreenViewport());

        parent.assMan.queueAddSkin();
        parent.assMan.manager.finishLoading();
        skin = parent.assMan.neonSkin;
    }

    @Override
    public void show() {
        // serves almost as a grid
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // any input will send a request from stage for response
        Gdx.input.setInputProcessor(stage);

        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        // fillX - fills the remaining space within its cell
        // uniformX - will force the size of any cells within
        //    the row to be the same size (currently none)
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        exit.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                parent.switchScreen(APPLICATION);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchScreen(PREFERENCES);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // act asks our stage to perform as we "draw" the curtains
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        // ^ maximum frame rate with this MathMin function

        stage.draw();
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
        stage.dispose();
    }
}
