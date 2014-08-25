package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.aqua.ludum.ld30.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Terrain {
	
	/**
	 * Make a new map from the provided file.
	 * @param pathToTmx
	 */
	public Terrain(String pathToTmx, OrthographicCamera camera) {
		TmxMapLoader mapLoader = new TmxMapLoader();
		System.out.println("Loading map.");
		TiledMap map = mapLoader.load(pathToTmx);
		System.out.println("Finished loading map.");
		this.tilesWide = (int) map.getProperties().get("width");
		this.tilesHigh = (int) map.getProperties().get("height");
		this.mapRenderer = new IsometricTiledMapRenderer((TiledMap) map, 1);
    	this.camera = camera;
    	this.units = new ArrayList<>();
    	this.blocks = new ArrayList<>();
    	this.players = new ArrayList<>();
    	this.neutralPlayer = new NeutralPlayer("Neutral", this);
    	this.players.add(this.neutralPlayer);
    	loadBlocks(map);
	}
	
	private void loadBlocks(TiledMap map) {
		for(int mapLayer = 0; mapLayer < map.getLayers().getCount(); mapLayer ++){
    		MapLayer mLayer = map.getLayers().get(mapLayer);
    		if(!mLayer.getName().equals("Blocks")) {
    			continue;
    		}
    		for(int mapObject = 0; mapObject < mLayer.getObjects().getCount(); mapObject ++){
    			RectangleMapObject mObject = (RectangleMapObject) mLayer.getObjects().get(mapObject);
    			Rectangle block = mObject.getRectangle();
    			block.y = this.getTilesHigh() * 32 - (block.y + block.height);
    			blocks.add(new Block(mObject.getRectangle()));
    		}
    	}
	}
	
	public void render(SpriteBatch batch) {
		// TODO z-index rendering order
		// render map
		batch.end();
		camera.translate(0, 16);
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
		camera.translate(0, -16);
		camera.update();
		batch.begin();
		// render units
		for(Unit unit : units) {
			unit.render(batch);
		}
		// render selection box
		for(Player player : players) {
			player.render(batch);
		}
	}
	
	public void update(float delta) {
		for(Unit unit : units) {
			unit.update(delta);
		}
		for(Player player : players) {
			player.update(delta);
		}
		for (int i = 0; i < units.size(); ++i) {
			if (units.get(i).getHP() < 0) {
				if (units.get(i).getPlayer() != this.getNeutralPlayer()) {
					units.add(new Spirit(units.get(i).getPlayer(), units.get(i).getPosition(), this));
				}
				units.remove(i);
				--i;
			}
		}
	}
	
	private void sortUnitsByDepth() {
		
	}
	
	public void addUnit(Unit unit) {
		this.units.add(unit);
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	
	public List<Unit> selectUnits(Rectangle selection) {
		return null;
	}
	
	public List<Unit> selectUnits(Player player, Rectangle selection) {
		ArrayList<Unit> selectedUnits = new ArrayList<>();
		selection.x += camera.position.x - Constants.SCREEN_WIDTH / 2;
		selection.y += camera.position.y - Constants.SCREEN_HEIGHT / 2;
		for(Unit unit : this.units) {
			// check if unit belongs to player
			if(unit.getPlayer() == player) {
				// check if unit is in selection
				if(selection.contains(Constants.worldToScreen(unit.getPosition(), tilesHigh))) {
					System.out.println("Unit selected");
					selectedUnits.add(unit);
				}
			}
		}
		return selectedUnits;
	}
	
	public Unit selectUnit(Vector2 point) {
		return null;
	}
	
	public Unit selectUnit(Player player, Vector2 point) {
		return null;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * Returns null if no block contains thhe given point.s
	 * @param point
	 * @return block if a block is found, null otherwise
	 */
	public Block getBlock(Vector2 point) {
		for(Block block : blocks) {
			if(block.getRectangle().contains(point)) {
				return block;
			}
		}
		return null;
	}
	
	public List<Vector2> getNeighbours(Vector2 point, Vector2 target) {			
		ArrayList<Vector2> neighbours = new ArrayList<>();	
		
		// check if target reachable
		if(reachable(point, target)) {
			neighbours.add(target);
		}
		
		// go through each block
		for(Block block : blocks) {
			Rectangle rect = block.getRectangle();
			float padding = Constants.PATHFINDING_CORNER_PADDING;
			// go through each point of block
			Vector2 pathPoint = new Vector2(rect.x - padding, rect.y - padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x + rect.width + padding, rect.y - padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x - padding, rect.y + rect.height + padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x + rect.width + padding, rect.y + rect.height + padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
		}
		
		// repeat process for buildings
		for(Unit unit : units) {
			if(!(unit instanceof Building)) {
				continue;
			}
			Rectangle rect = ((Building) unit).getRectangle();
			float padding = Constants.PATHFINDING_CORNER_PADDING;
			// go through each point of block
			Vector2 pathPoint = new Vector2(rect.x - padding, rect.y - padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x + rect.width + padding, rect.y - padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x - padding, rect.y + rect.height + padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
			pathPoint = new Vector2(rect.x + rect.width + padding, rect.y + rect.height + padding);
			if (reachable(point, pathPoint)) {
				neighbours.add(pathPoint);
			}
		}
		
		return neighbours;
	}
	
	private boolean reachable(Vector2 point, Vector2 target) {
		for (Block block : this.blocks) {
			Rectangle rect = block.getRectangle();
			Vector2 topLeft = new Vector2(rect.x, rect.y);
			Vector2 topRight = new Vector2(rect.x + rect.width, rect.y);
			Vector2 bottomLeft = new Vector2(rect.x, rect.y + rect.height);
			Vector2 bottomRight = new Vector2(rect.x + rect.width, rect.y + rect.height);
			Vector2 intersection = new Vector2();
			if (Intersector.intersectSegments(point, target, topLeft, topRight, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, topLeft, bottomLeft, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, topRight, bottomRight, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, bottomLeft, bottomRight, intersection)) {
				return false;
			}
			if (rect.contains(point)) {
				return false;
			}
			if (rect.contains(target)) {
				return false;
			}
		}
		for (Unit unit : this.units) {
			if (!(unit instanceof Building)) {
				continue;
			}
			Rectangle rect = ((Building) unit).getRectangle();
			Vector2 topLeft = new Vector2(rect.x, rect.y);
			Vector2 topRight = new Vector2(rect.x + rect.width, rect.y);
			Vector2 bottomLeft = new Vector2(rect.x, rect.y + rect.height);
			Vector2 bottomRight = new Vector2(rect.x + rect.width, rect.y + rect.height);
			Vector2 intersection = new Vector2();
			if (Intersector.intersectSegments(point, target, topLeft, topRight, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, topLeft, bottomLeft, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, topRight, bottomRight, intersection)) {
				return false;
			}
			if (Intersector.intersectSegments(point, target, bottomLeft, bottomRight, intersection)) {
				return false;
			}
			if (rect.contains(point)) {
				return false;
			}
			if (rect.contains(target)) {
				return false;
			}
		}
		return true;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getNeutralPlayer() {
		return neutralPlayer;
	}
	
	public int getWidth() {
		int TILEWIDTH = 32;
		return this.getTilesWide() * TILEWIDTH;
	}
	
	public int getHeight() {
		int TILEHEIGHT = 32;
		return this.getTilesHigh() * TILEHEIGHT;
	}
	
	public int getTilesWide() {
		return tilesWide;
	}
	
	public int getTilesHigh() {
		return tilesHigh;
	}
	
	private List<Unit> units;
	private List<Block> blocks;
	private List<Player> players;
	private Player neutralPlayer;
	
	private final int tilesWide, tilesHigh;
	
	private OrthographicCamera camera;
	private TiledMapRenderer mapRenderer;
}
