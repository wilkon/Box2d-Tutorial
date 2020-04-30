package com.box2d.game;

import com.badlogic.gdx.Game;
import com.box2d.game.loaders.B2dAssetLoader;
import com.box2d.game.preferences.AppPreferences;
import com.box2d.game.views.*;

import static com.box2d.game.constants.GameViews.*;

public class Box2dTutorial extends Game {

	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;

	private AppPreferences appPreferences;

	public B2dAssetLoader assMan = new B2dAssetLoader();
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		appPreferences=new AppPreferences();
		setScreen(loadingScreen);

		assMan.queueAddMusic();
		assMan.manager.finishLoading();

		assMan.gameMusic.setLooping(true);
		assMan.gameMusic.play();
	}

	@Override
	public void render () {
		super.render();
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

	public AppPreferences getPreferences(){
		return appPreferences;
	}

	@Override
	public void dispose () {
		assMan.manager.dispose();
	}
}
