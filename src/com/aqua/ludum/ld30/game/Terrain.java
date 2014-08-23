package com.aqua.ludum.ld30.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Terrain {
	
	/**
	 * Make a new map from the provided file.
	 * @param file
	 */
	public Terrain(String file) {
		TmxMapLoader mapLoader = new TmxMapLoader();
		System.out.println("Loading map.");
		TiledMap map = mapLoader.load(file);
		System.out.println("Finished loading map.");
		this.mapRenderer = new IsometricTiledMapRenderer((TiledMap) map, 1);
		OrthographicCamera camera = new OrthographicCamera();
    	camera = new OrthographicCamera(960, 640);
    	camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    	camera.update();
    	this.mapRenderer.setView(camera);
	}
	
	public void render(SpriteBatch batch) {
		
	}
	
	public void update(float delta) {
		
	}
	
	private void sortUnitsByDepth() {
		
	}
	
	public List<Unit> getUnits() {
		
	}
	
	public List<Unit> selectUnits(Rectangle selection) {
		return null;
	}
	
	public List<Unit> selectUnits(Player player, Rectangle selection) {
		return null;
	}
	
	public Unit selectUnit(Vector2 point) {
		return null;
	}
	
	public Unit selectUnit(Player player, Vector2 point) {
		return null;
	}
	
	public List<Block> getBlocks() {
		
	}
	
	public Block getBlock(Vector2 point) {
		
	}
	
	public List<Player> getPlayers() {
		
	}
	
	public Player getNeutralPlayer() {
		
	}
	
	public List<Unit> units;
	public List<Block> blocks;
	public List<Player> players;
	public Player neutralPlayer;
	
	private TiledMapRenderer mapRenderer;
}
