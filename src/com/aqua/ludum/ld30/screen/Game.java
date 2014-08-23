package com.aqua.ludum.ld30.screen;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * A class that handles updating, rendering,
 * and changing screens, as well as coordinating transitions.
 * @author GeoYS_2
 *
 */
public abstract class Game implements ApplicationListener, InputProcessor{
	
	private HashMap<Integer, UpdateableScreen> screens;
	private UpdateableScreen currentScreen = null, previousScreen = null;
	
	/**
	 * If this is the main game object, pass the title 
	 * for it to show up in the title bar.
	 * @param title
	 */
	public Game(String title){
		while(Gdx.input == null){
			System.out.println("Waiting for input...");
		}
		Gdx.input.setInputProcessor(this);
		Gdx.graphics.setTitle(title);
		screens = new HashMap<Integer, UpdateableScreen>();
	}
	
	public Game(){
		while(Gdx.input == null){
			System.out.println("Waiting for input...");
		}
		Gdx.input.setInputProcessor(this);
		screens = new HashMap<Integer, UpdateableScreen>();
	}
	
	public void addScreen(UpdateableScreen screen){
		screens.put(screen.getID(), screen);
	}
	
	public void initScreens(){
		for(UpdateableScreen screen : screens.values()){
			screen.init(this);
		}
	}
	
	/**
	 * Calls show() on the entering screen, and hide() on the leaving screen.
	 * @param id
	 */
	public void enterScreen(int id){
		previousScreen = currentScreen;
		if(currentScreen != null){
			currentScreen.hide();
		}
		currentScreen = screens.get(id);
		currentScreen.show();
		
		if(previousScreen != null && previousScreen instanceof GameScreen){
			GameScreen prevGameScreen = (GameScreen) previousScreen;
			prevGameScreen.startOutTransition(currentScreen);
		}
		if(currentScreen instanceof GameScreen){
			GameScreen currGameScreen = (GameScreen) currentScreen;
			currGameScreen.startInTransition(previousScreen);
		}
	}


	@Override
	public void render() {
		if(previousScreen != null && previousScreen instanceof GameScreen){
			GameScreen prevGameScreen = (GameScreen) previousScreen;
			if(!prevGameScreen.outTransitionFinished()){
				prevGameScreen.renderOut(Gdx.graphics.getDeltaTime());
				return;
			}
			else{
				previousScreen = null;
			}
		}
		if(currentScreen instanceof GameScreen){
			GameScreen currGameScreen = (GameScreen) currentScreen;
			if(!currGameScreen.inTransitionFinished()){
				currGameScreen.renderIn(Gdx.graphics.getDeltaTime());
				return;
			}
		}
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}

	/**
	 * Add screens to your game here. 
	 * Also call initScreens() once you're done adding screens.
	 * Also enter() into the first screen.
	 */
	@Override
	public abstract void create();

	private boolean isTransitioning(){
		if(previousScreen != null && previousScreen instanceof GameScreen){
			GameScreen prevGameScreen = (GameScreen) previousScreen;
			if(!prevGameScreen.outTransitionFinished()){
				//System.out.println("transitioning");
				return true;
			}
		}
		if(currentScreen instanceof GameScreen){
			GameScreen currGameScreen = (GameScreen) currentScreen;
			if(!currGameScreen.inTransitionFinished()){
				//System.out.println("transitioning");
				return true;
			}
		}
		//System.out.println("Not transitioning");
		return false;
	}	

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	@Override
	public void pause() {
		currentScreen.pause();
	}

	@Override
	public void resume() {
		currentScreen.resume();
	}

	@Override
	public void dispose() {
		for(UpdateableScreen screen : screens.values()){
			screen.dispose();
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return isTransitioning() ? false : currentScreen.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return isTransitioning() ? false : currentScreen.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return isTransitioning() ? false : currentScreen.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return isTransitioning() ? false : currentScreen.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return isTransitioning() ? false : currentScreen.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return isTransitioning() ? false : currentScreen.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return isTransitioning() ? false : currentScreen.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return isTransitioning() ? false : currentScreen.scrolled(amount);
	}
}
