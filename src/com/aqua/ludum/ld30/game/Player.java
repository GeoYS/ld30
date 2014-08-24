package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Player {
	
	public Player(String name, Terrain terrain) {
		this.name = name;
		this.terrain = terrain;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void render(SpriteBatch batch) {
	}
	
	public abstract void update(float delta);
	
	public InputProcessor getListener() {
		return null;
	}
	
	protected Terrain getTerrain() {
		return this.terrain;
	}
	
	private String name;
	private Terrain terrain;
	
}
