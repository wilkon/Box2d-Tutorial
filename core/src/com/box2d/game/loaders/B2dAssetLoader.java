package com.box2d.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class B2dAssetLoader {
    public final AssetManager manager = new AssetManager();

    private String textureAtlas = "images/loading.atlas";

    public final String gameImages = "images/game.atlas";
    public final String loadingImages = "images/loading.atlas";

    public Sound boingSound;
    public Sound pingSound;

    public Music gameMusic;

    public Skin neonSkin;

    public void queueAddImages(){
        manager.load(gameImages, TextureAtlas.class);
    }
    public void queueAddSounds(){
        boingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/boing.wav"));
        pingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/ping.wav"));
    }

    public void queueAddMusic(){
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/grapes-I dunno.mp3"));
    }

    public void queueAddSkin(){
        neonSkin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
    }


    public void queueAddLoadingImages(){
        manager.load(loadingImages, TextureAtlas.class);
    }
    public void queueAddFonts(){

    }

    public void queueAddParticleEffects(){

    }
}
