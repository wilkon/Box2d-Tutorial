package com.box2d.game.loaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class B2dAssetLoader {
    public final AssetManager manager = new AssetManager();

    public final String playerImage = "images/player.png";
    public final String enemyImage = "images/enemy.png";

    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }
}
