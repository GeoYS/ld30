package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.aqua.ludum.ld30.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, final Terrain terrain, final OrthographicCamera camera) {
		super(name, terrain);
		this.currentSelection = new Selection();
		selectedUnits = new ArrayList<>();
		this.renderer = new ShapeRenderer();
		this.camera = camera;
		inputListener = new InputProcessor() {

			private int lastX, lastY;
			private boolean[] isDown = new boolean[] {false, false, false};
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// select units in selection rectangle
				if(isDown[Input.Buttons.LEFT]) {
					Rectangle worldSelection = new Rectangle(currentSelection.getRectangle());
					selectedUnits = terrain.selectUnits(HumanPlayer.this, worldSelection);
					currentSelection.setActive(false);
				}
				// command units
				if(isDown[Input.Buttons.RIGHT]) {
					Vector2 screenPos = new Vector2(screenX - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - screenY).add(camera.position.x, camera.position.y);
					int layer = 0;
					int ringPos = 0;
					
					for (int i = 0; i < selectedUnits.size(); ++i) {
						Unit unit = selectedUnits.get(i);
						if (i == 0) {
							unit.commandMove(Constants.screenToWorld(screenPos, terrain.getTilesHigh()));
							layer++;
							continue;
						}
						float degrees = (float) (2 * Math.PI / (6.0 * layer));
						int coprime = layer < Constants.COPRIMES.length ? Constants.COPRIMES[layer] : 1;
						Vector2 displacement = new Vector2((float) Math.cos(degrees * ringPos * coprime) * layer * 32, (float) Math.sin(degrees * ringPos * coprime) * layer * 32);
						unit.commandMove(Constants.screenToWorld(screenPos.cpy().add(displacement), terrain.getTilesHigh()));
						ringPos++;
						if (ringPos >= 6 * layer) {
							layer++;
							ringPos = 0;
						}
					}
					/*for(Unit unit : selectedUnits) {
						unit.commandMove(Constants.screenToWorld(screenPos, terrain.getTilesHigh()));
					}*/
				}
				isDown[button] = false;
				return true; // touchUp used up
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if(isDown[Input.Buttons.LEFT]) {
					currentSelection.setNewPos(new Vector2(screenX, Constants.SCREEN_HEIGHT - screenY));
					currentSelection.update();
				}
				if(isDown[Input.Buttons.MIDDLE]){
					// update camera
					camera.translate(-(screenX - lastX), screenY - lastY);
					camera.update();
					lastX = screenX;
					lastY = screenY;
				}
				return true; // touchDragged used
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				isDown[button] = true;
				if(isDown[Input.Buttons.LEFT]) {
					currentSelection.setOldPos(new Vector2(screenX, Constants.SCREEN_HEIGHT - screenY));
					currentSelection.setActive(true);
				}
				// camera pos
				if(isDown[Input.Buttons.MIDDLE]){
					// update camera
					lastX = screenX;
					lastY = screenY;
				}
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
				Vector2 mouseWorldPos = Constants
						.screenToWorld(
								mouseToScreen(
										new Vector2(Gdx.input.getX(), Gdx.input.getY()))
										.add(camera.position.x, camera.position.y),
								terrain.getTilesHigh());
				if(keycode == Input.Keys.X) {
					terrain.addUnit(new Worker(HumanPlayer.this, mouseWorldPos, terrain));
				}
				return true;
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
	public void render(SpriteBatch batch) {
		super.render(batch);
		batch.end();
		currentSelection.render();
		batch.begin();
		renderer.setColor(Color.GREEN);
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Line);
		for(Unit unit : selectedUnits) {
			Vector2 screenPos = Constants.worldToScreen(unit.getPosition(), getTerrain().getTilesHigh());
			renderer.ellipse(screenPos.x - unit.getRadius(), screenPos.y - unit.getRadius() / 2, unit.getRadius() * 2, unit.getRadius());
		}
		renderer.end();
	}
	
	@Override
	public InputProcessor getListener() {
		return inputListener;
	}
	
	/**
	 * Weird method.
	 * @param mousePos
	 * @return
	 */
	private Vector2 mouseToScreen(Vector2 mousePos) {
		return new Vector2(mousePos.x - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - mousePos.y);
	}
	
	private List<Unit> selectedUnits;
	private InputProcessor inputListener;
	private Selection currentSelection;
	private ShapeRenderer renderer;
	private OrthographicCamera camera;
	
	private class Selection {
		
		private float ox, oy, nx, ny;
		private Rectangle rectangle;
		private boolean active;
		private ShapeRenderer renderer;
		
		public Selection() {
			rectangle = new Rectangle();
			this.renderer = new ShapeRenderer();
		}
		
		public void render() {
			if(!active) {
				return;
			}
			update();
			this.renderer.setColor(Color.GREEN);
			this.renderer.begin(ShapeType.Line);
			this.renderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			this.renderer.end();
		}
		
		public void setOldPos(Vector2 old) {
			ox = nx = old.x;
			oy = ny = old.y;
		}
		
		public void setNewPos(Vector2 newPos) {
			this.nx = newPos.x;
			this.ny = newPos.y;
		}
		
		public void setActive(boolean isActive) {
			this.active = isActive;
		}
		
		public void update() {
			float x = ox < nx ? ox : nx,
					y = oy < ny ? oy : ny,
					width = Math.abs(ox - nx),
					height = Math.abs(oy - ny);
			rectangle.setPosition(x, y);
			rectangle.setSize(width, height);
		}
		
		public Rectangle getRectangle() {
			update();
			return rectangle;
		}
	}
}
