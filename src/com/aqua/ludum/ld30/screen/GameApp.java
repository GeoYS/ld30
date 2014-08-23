package com.aqua.ludum.ld30.screen;

import com.badlogic.gdx.ApplicationListener;

public abstract class GameApp implements ApplicationListener{

	private Game game;
	
	/**
	 * Only called once to create an instance of your Game.
	 * @return 
	 */
	public abstract Game game();
	
	@Override
	public void create() {
		game = game();
		game.create();
	}

	@Override
	public void resize(int width, int height) {
		game.resize(width, height);
	}

	@Override
	public void render() {
		game.render();
	}

	@Override
	public void pause() {
		game.pause();
	}

	@Override
	public void resume() {
		game.resume();
	}

	@Override
	public void dispose() {
		game.dispose();
	}

}
