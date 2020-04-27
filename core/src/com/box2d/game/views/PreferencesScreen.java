package com.box2d.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.box2d.game.Box2dTutorial;

import static com.box2d.game.constants.GameViews.*;

public class PreferencesScreen implements Screen {
    private Box2dTutorial parent;
    private Stage stage;

    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    public PreferencesScreen(Box2dTutorial parent){
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event){
                parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                return false;
            }
        });

        final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f, false, skin);
        volumeSoundSlider.setValue(parent.getPreferences().getSoundVolume());
        volumeSoundSlider.addListener(new EventListener(){
            @Override
            public boolean handle(Event event){
                parent.getPreferences().setMusicVolume(volumeSoundSlider.getValue());
                return false;
            }
        });

        final CheckBox musicCheckBox = new CheckBox(null, skin);
        musicCheckBox.setChecked(parent.getPreferences().isMusicEnabled());
        musicCheckBox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event){
                boolean enabled = musicCheckBox.isChecked();
                parent.getPreferences().setMusicEnabled(enabled);
                return false;
            }
        });

        final CheckBox soundCheckBox = new CheckBox(null, skin);
        soundCheckBox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
        soundCheckBox.addListener(new EventListener(){
            @Override
            public boolean handle(Event event){
                boolean enabled = soundCheckBox.isChecked();
                parent.getPreferences().setSoundEffectEnabled(enabled);
                return false;
            }
        });

        final TextButton backButton = new TextButton("Back", skin);

        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                parent.switchScreen(MENU);
            }
        });

        titleLabel = new Label("Preferences", skin);
        volumeMusicLabel = new Label("Music Volume", skin);
        volumeSoundLabel = new Label("Sound Volume", skin);
        musicOnOffLabel = new Label("Music", skin);
        soundOnOffLabel = new Label("Sound", skin);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
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
