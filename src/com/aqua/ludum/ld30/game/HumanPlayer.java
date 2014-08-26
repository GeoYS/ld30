package com.aqua.ludum.ld30.game;

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
	
	public HumanPlayer(String name, final OrthographicCamera camera) {
		super(name);
		this.currentSelection = new Selection();
		this.renderer = new ShapeRenderer();
		this.camera = camera;
	}
	
	public void setInputListener() {
		final Terrain terrain = getTerrain();
		inputListener = new InputProcessor() {

			private int lastX, lastY;
			private boolean[] isDown = new boolean[] {false, false, false};
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(isDown[Input.Buttons.LEFT]) {
					// select units in selection rectangle
					Rectangle worldSelection = new Rectangle(currentSelection.getRectangle());
					setSelectedUnits(terrain.selectUnits(HumanPlayer.this, worldSelection));
					currentSelection.setActive(false);

					/*Vector2 screenPos = new Vector2(screenX - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - screenY).add(camera.position.x, camera.position.y);
					for(Unit unit : terrain.getUnits()) {
						// check if unit belongs to player
						if(unit.getPlayer() == HumanPlayer.this) {
							// check if mouse is inside unit's circle
							if(unit.getCollisionShape().contains(Constants.screenToWorld(screenPos, terrain.getTilesHigh()))) {
								getSelectedUnits().add(unit);
							}
						}
					}*/
				}
				// command units
				if(isDown[Input.Buttons.RIGHT]) {
					Vector2 screenPos = new Vector2(screenX - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - screenY).add(camera.position.x, camera.position.y);
					moveSelectedUnits(Constants.screenToWorld(screenPos, terrain.getTilesHigh()));
					/*for(Unit unit : selectedUnits) {
						unit.commandMove(Constants.screenToWorld(screenPos, terrain.getTilesHigh()));
					}*/
					// command attack
					Vector2 mouseWorld = Constants.screenToWorld(screenPos, terrain.getTilesHigh());
					for(Unit unit : terrain.getUnits()) {
						if(unit.getPlayer() != HumanPlayer.this){
							if(unit.getCollisionShape().contains(mouseWorld) || unit.getScreenRectangle().contains(screenPos)){
								HumanPlayer.this.attackWithSelectedUnits(unit);
								break;
							}
						}
					}
					// enter building
					for(Unit unit : terrain.getUnits()) {
						if(unit instanceof SpawnBuilding) {
							if(unit.getCollisionShape().contains(mouseWorld)){
								HumanPlayer.this.enterBuildingWithSelectedUnits((SpawnBuilding) unit);
								break;
							}
						}
					}
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
				else if(keycode == Input.Keys.C) {
					terrain.addUnit(new Soldier(HumanPlayer.this, mouseWorldPos, terrain));
				}
				else if(keycode == Input.Keys.V) {
					terrain.addUnit(new Fast(HumanPlayer.this, mouseWorldPos, terrain));
				}
				else if(keycode == Input.Keys.B) {
					terrain.addUnit(new Tank(HumanPlayer.this, mouseWorldPos, terrain));
				}
				else if(keycode == Input.Keys.N) {
					terrain.addUnit(new Spirit(HumanPlayer.this, mouseWorldPos, terrain));
				}
				else if(keycode == Input.Keys.M) {
					terrain.addUnit(new Ranged(HumanPlayer.this, mouseWorldPos, terrain));
				}
				else if (keycode == Input.Keys.Q) {
					for (Unit unit : HumanPlayer.this.getSelectedUnits()) {
						if (!(unit instanceof Spirit) && !(unit instanceof Temple)) {
							if (unit instanceof SpawnBuilding) {
								int n = ((SpawnBuilding) unit).spiritCount;
								unit.hp = -10;
								for (int i = 0; i < n; ++i) {
									terrain.spawnUnit(new Spirit(HumanPlayer.this, unit.position, terrain));
								}
							}
							else {
								unit.hp = -10;
								terrain.spawnUnit(new Spirit(HumanPlayer.this, unit.position, terrain));
							}
						}
					}
				}
				
				// worker building
				int workerCount = 0;
				Worker selectedWorker = null;
				for(Unit unit : HumanPlayer.this.getSelectedUnits()) {
					if(unit instanceof Worker) {
						workerCount ++;
						selectedWorker = (Worker) unit;
					}
				}
				if(workerCount == 1) {
					selectedWorker.handleKey(keycode);
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
	public void render(SpriteBatch batch) {
		super.render(batch);
		batch.end();
		currentSelection.render();
		batch.begin();
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Line);
		for(Unit unit : getSelectedUnits()) {
			renderer.setColor(Color.GREEN);
			// draw selection circle
			Vector2 screenPos = Constants.worldToScreen(unit.getPosition(), getTerrain().getTilesHigh());
			renderer.ellipse(screenPos.x - unit.getRadius(), screenPos.y - unit.getRadius() / 2, unit.getRadius() * 2, unit.getRadius());
			// draw target points
			List<Vector2> pathPoints = unit.getPath().getPoints();
			if(!pathPoints.isEmpty()) {
				Vector2 targetPos = Constants.worldToScreen(pathPoints.get(pathPoints.size() - 1), getTerrain().getTilesHigh());
				renderer.circle(targetPos.x, targetPos.y, 2);
			}
		}
		renderer.end();
		renderer.begin(ShapeType.Filled);
		for(Unit unit : this.getTerrain().getUnits()) {
			// draw unit health bars
			final float HEALTH_DIST_SQUARED = 100 * 100;
			float dist2 = unit.getPosition().cpy().dst2(this.mouseWorldPos());
			if(this.getSelectedUnits().contains(unit) || dist2 <= HEALTH_DIST_SQUARED) {
				renderUnitHealth(renderer, unit);
			}
		}
		for(Unit unit : getTerrain().getUnits()) {
			if(unit.getPlayer() == getTerrain().getNeutralPlayer()) {
				renderer.setColor(Color.GRAY);
			}
			else if(unit.getPlayer() == this) {
				renderer.setColor(Color.GREEN);
			}
			else {
				renderer.setColor(Color.RED);
			}
			renderer.rect(unit.getScreenPosition().x - 1.5f,
					unit.getScreenPosition().y + 32,
					3,
					3);
		}
		renderer.end();
	}
	
	private Vector2 mouseWorldPos() {
		Vector2 mouseScreen = new Vector2(Gdx.input.getX() - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - Gdx.input.getY()).add(camera.position.x, camera.position.y);
		return Constants.screenToWorld(mouseScreen, this.getTerrain().getTilesHigh());
	}
	
	private void renderUnitHealth(ShapeRenderer renderer, Unit unit) {
		final float MAX_HEALTH_WIDTH = 40; // pixels
		if(unit.getPlayer() == this) {
			renderer.setColor(Color.GREEN);
		}
		else if(unit.getPlayer() instanceof NeutralPlayer) {
			renderer.setColor(Color.GRAY);
		}
		else {
			renderer.setColor(Color.RED);
		}
		renderer.rect(unit.getScreenPosition().x - MAX_HEALTH_WIDTH / 2,
				unit.getScreenPosition().y - 10,
				unit.getHP() / unit.getStartHP() * MAX_HEALTH_WIDTH,
				2);
		// draw shp bar
		renderer.setColor(Color.BLUE);
		renderer.rect(unit.getScreenPosition().x - MAX_HEALTH_WIDTH / 2,
				unit.getScreenPosition().y - 14,
				unit.getSHP() / unit.getStartSHP() * MAX_HEALTH_WIDTH,
				2);
		if(unit instanceof SpawnBuilding) {
			SpawnBuilding spawnBuilding = (SpawnBuilding) unit;
			if(spawnBuilding.spiritCount > 0) {
				float percentage = 1 - spawnBuilding.timeToSpawn / spawnBuilding.timeUntilSpawn();
				renderer.setColor(Color.PINK);
				renderer.rect(unit.getScreenPosition().x - MAX_HEALTH_WIDTH / 2,
						unit.getScreenPosition().y - 18,
						percentage * MAX_HEALTH_WIDTH,
						2);
				for(int i = 0; i < spawnBuilding.spiritCount; i ++) {
					renderer.setColor(Color.BLUE);
					renderer.rect(unit.getScreenPosition().x - MAX_HEALTH_WIDTH / 2 + i * 3,
							unit.getScreenPosition().y - 22,
							2,
							2);
				}
			}
		}
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
