package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.box2d.game.Box2dTutorial;

public class MenuScreen implements Screen {
    private Box2dTutorial parent;
    private Stage stage;

    public MenuScreen(Box2dTutorial parent){
        this.parent = parent;
        this.stage = new Stage(new ScreenViewport());

        // any input will send a request from stage for response
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // serves almost as a grid
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

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

    }
}
