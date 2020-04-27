package com.box2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.box2d.game.views.*;

import static com.box2d.game.constants.GameViews.*;

public class Box2dTutorial extends Game {

	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void switchScreen(int screen){
		switch(screen){
			case MENU :
				if(menuScreen == null)
					menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES :
				if(preferencesScreen == null)
					preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
			case APPLICATION :
				if(mainScreen == null)
					mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case ENDGAME :
				if(endScreen == null)
					endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;
		}
	}

	@Override
	public void dispose () {

	}
}
