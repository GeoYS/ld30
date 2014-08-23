package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Terrain {
	
	/**
	 * Make a new map from the provided file.
	 * @param file
	 */
	public Terrain(String file) {
		
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
	
	public List<Vector2> getNeighbours(Vector2 point) {
		
	}
	
	
	
	public List<Player> getPlayers() {
		
	}
	
	public Player getNeutralPlayer() {
		
	}
	
	private List<Unit> units;
	private List<Block> blocks;
	private List<Player> players;
	private Player neutralPlayer;
	
}
