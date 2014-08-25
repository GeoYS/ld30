package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Player {
	
	public Player(String name) {
		this.name = name;
		this.selectedUnits = new ArrayList<>();
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void render(SpriteBatch batch) {
	}
	
	public void update(float delta) {
		List<Unit> units = this.getSelectedUnits();
		for (int i = 0; i < units.size(); ++i) {
			if (units.get(i).getHP() < 0) {
				units.remove(i);
				--i;
			}
		}
	}
	
	protected void moveSelectedUnits(Vector2 position) {
		List<Unit> units = new ArrayList<>();
		for(Unit unit : getSelectedUnits()) {
			if(!(unit instanceof Building)) {
				units.add(unit);
			}
		}
		Formation formation = new Formation(32.0f);
		int failsafe = units.size() * 5;
		for (int i = 0; i < units.size(); ++i) {
			Unit unit = units.get(i);
			Vector2 next = formation.getNextPosition();
			if (next == null) {
				break;
			}
			if (unit.commandMove(position.cpy().add(next), units)) {
				formation.fillPosition();
			}
			else {
				--i;
				--failsafe;
				if (failsafe <= 0) {
					break;
				}
			}
		}
	}
	
	protected void attackWithSelectedUnits(Unit target) {
		for (Unit unit : this.selectedUnits) {
			unit.commandAttack(target);
		}
	}
	
	protected void enterBuildingWithSelectedUnits(SpawnBuilding target) {
		for (Unit unit : this.selectedUnits) {
			if(unit instanceof Spirit) {
				((Spirit) unit).commandTargetBuilding(target);
			}
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
