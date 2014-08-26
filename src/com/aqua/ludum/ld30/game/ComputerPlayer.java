package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;


public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if (Math.random() < delta / 20) {
			for (Unit unit : this.getTerrain().getUnits()) {
				if (!(unit instanceof Spirit) && !(unit instanceof Building) && unit.player == this) {
					getSelectedUnits().add(unit);
				}
			}
			Temple temple = null;
			for(Unit u : this.getTerrain().getUnits()) {
				if(u instanceof Temple && u.getPlayer() != this) {
					temple = (Temple) u;
				}
			}
			attackWithSelectedUnits(temple);
			getSelectedUnits().clear();
			return;
		}
		for(Unit unit : this.getTerrain().getUnits()) {
			if(unit.getPlayer() == this) {
				if (unit.targetUnit != null || (unit instanceof Spirit && (((Spirit) unit).targetBuilding != null)) && Math.random() > delta / 10) {
					continue;
				}
				if(unit instanceof Spirit) {
					List<Unit> workers = new ArrayList<>();
					for(Unit u : this.getTerrain().getUnits()) {
						if(u instanceof Worker && u.getPlayer() == getTerrain().getNeutralPlayer()) {
							workers.add(u);
						}
					}
					if(workers.isEmpty()) {
						for(Unit u : this.getTerrain().getUnits()) {
							if(u instanceof Worker && u.getPlayer() != this) {
								workers.add(u);
							}
						}
					}
					if (workers.isEmpty()) {
						for (Unit u : this.getTerrain().getUnits()) {
							if (u instanceof SpawnBuilding && u.player == this) {
								Spirit spirit = (Spirit) unit;
								spirit.commandTargetBuilding((SpawnBuilding) u);
							}
						}
					}
					if(workers.isEmpty()) {
						for(Unit u : this.getTerrain().getUnits()) {
							if(!(u instanceof Building) && !(u instanceof Spirit) && u.getPlayer() != this) {
								workers.add(u);
							}
						}
					}
					if(!workers.isEmpty()) {
						this.getSelectedUnits().add(unit);
						this.attackWithSelectedUnits(workers.get((int) (Math.random() * workers.size())));
						this.getSelectedUnits().remove(unit);
					}
				}
				else if (unit instanceof Worker) {
					if(Math.random() < delta / 10) {
						Worker worker = (Worker) unit;
						if(Math.random() < 0.25) {
							worker.becomeBuilding(new SoldierSpawn(this, worker.position, getTerrain()));
						}
						else if(Math.random() < 0.33) {
							worker.becomeBuilding(new RangeSpawn(this, worker.position, getTerrain()));
						}
						else if(Math.random() < 0.5) {
							worker.becomeBuilding(new FastSpawn(this, worker.position, getTerrain()));
						}
						else {
							worker.becomeBuilding(new TankSpawn(this, worker.position, getTerrain()));
						}
					}
					else if (!isUnitMoving(unit) || Math.random() < delta) {
						Temple temple = null;
						for(Unit u : this.getTerrain().getUnits()) {
							if(u instanceof Temple && u.getPlayer() == this) {
								temple = (Temple) u;
							}
						}
						if(temple == null) {
							return;
						}
						float x, y;
						x = (float) (Math.random() * 256) - 128;
						y = (float) (Math.random() * 256) - 128;
						Vector2 newPos = new Vector2(x, y).add(temple.position);

						this.getSelectedUnits().add(unit);
						this.moveSelectedUnits(newPos);
						this.getSelectedUnits().remove(unit);
					}
				}
				else if (unit instanceof Tank) {
					List<Unit> buildings = new ArrayList<>();
					for(Unit u : this.getTerrain().getUnits()) {
						if(u instanceof Building 
								&& u.getPlayer() != getTerrain().getNeutralPlayer()
								&& u.getPlayer() != this) {
							buildings.add(u);
						}
					}
					if(buildings.isEmpty()) {
						continue;
					}
					this.getSelectedUnits().add(unit);
					this.attackWithSelectedUnits(buildings.get((int) (Math.random() * buildings.size())));
					this.getSelectedUnits().remove(unit);
				}
				else {
					List<Unit> enemies = new ArrayList<>();
					for(Unit u : this.getTerrain().getUnits()) {
						if(/*u.getPlayer() != getTerrain().getNeutralPlayer()
								&&*/ u.getPlayer() != this) {
							enemies.add(u);
						}
					}
					this.getSelectedUnits().add(unit);
					this.attackWithSelectedUnits(enemies.get((int) (Math.random() * enemies.size())));
					this.getSelectedUnits().remove(unit);
				}
			}
		}
	}
	
	private boolean isUnitMoving(Unit unit) {
		return !unit.getPath().getPoints().isEmpty();
	}
	
}
