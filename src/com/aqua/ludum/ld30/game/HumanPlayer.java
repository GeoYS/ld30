package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, final Terrain terrain, final OrthographicCamera camera) {
		super(name, terrain);
		this.currentSelection = new Selection();
		inputListener = new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// select units in selection rectangle
				currentSelection.setActive(false);
				return true; // touchUp used up
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				currentSelection.setNewPos(screenX, screenY);
				return true; // touchDragged used
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				currentSelection.setNewPos(screenX, screenY);
				currentSelection.setActive(true);
				return true; // touchDown used up
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
	
	private OrthographicCamera camera;
	private InputProcessor inputListener;
	private Selection currentSelection;
	
	private class Selection {
		
		private float ox, oy, nx, ny;
		private Rectangle rectangle;
		private boolean active;
		
		public Selection() {
			rectangle = new Rectangle();
		}
		
		public void setOldPos(float x, float y) {
			ox = nx = x;
			oy = ny = y;
		}
		
		public void setNewPos(float nx, float ny) {
			this.nx = nx;
			this.ny = ny;
		}
		
		public void setActive(boolean isActive) {
			this.active = isActive;
		}
		
		public Rectangle getRectangle() {
			float x = ox < nx ? ox : nx,
					y = oy < ny ? oy : ny,
					width = Math.abs(ox - nx),
					height = Math.abs(oy - ny);
			rectangle.setPosition(x, y);
			rectangle.setSize(width, height);
			return rectangle;
		}
	}
}
