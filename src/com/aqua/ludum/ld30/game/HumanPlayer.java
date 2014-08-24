package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, Terrain terrain, OrthographicCamera camera) {
		super(name, terrain);
		inputListener = new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}
		};
	}
	
	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public InputProcessor getListener() {
		return inputListener;
	}
	
	private InputProcessor inputListener;
	
}
