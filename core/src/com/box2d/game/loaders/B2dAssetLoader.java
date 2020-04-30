package com.box2d.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class B2dAssetLoader {
    public final AssetManager manager = new AssetManager();

    public final String playerImage = "images/player.png";
    public final String enemyImage = "images/enemy.png";

    public Sound boingSound;
    public Sound pingSound;

    public Music gameMusic;

    public Skin neonSkin;

    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
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
}
