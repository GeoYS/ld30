package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.aqua.ludum.ld30.Constants;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Player {
	
	public Player(String name, Terrain terrain) {
		this.name = name;
		this.terrain = terrain;
		this.selectedUnits = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void render(SpriteBatch batch) {
	}
	
	public abstract void update(float delta);
	
	protected void moveSelectedUnits(Vector2 position) {
		Formation formation = new Formation(32.0f);
		for (int i = 0; i < getSelectedUnits().size(); ++i) {
			Unit unit = getSelectedUnits().get(i);
			Vector2 next = formation.getNextPosition();
			if (next == null) {
				break;
			}
			if (unit.commandMove(position.cpy().add(next), getSelectedUnits())) {
				formation.fillPosition();
			}
			else {
				--i;
			}
		}
	}
	
	protected void attackWithSelectedUnits(Unit target) {
		for (Unit unit : this.selectedUnits) {
			unit.commandAttack(target);
		}
	}
	
	public InputProcessor getListener() {
		return null;
	}
	
	protected Terrain getTerrain() {
		return this.terrain;
	}
	
	protected List<Unit> getSelectedUnits() {
		return this.selectedUnits;
	}
	
	protected void setSelectedUnits(List<Unit> units) {
		this.selectedUnits = units;
	}
	
	private String name;
	private Terrain terrain;
	private List<Unit> selectedUnits;
	
}
