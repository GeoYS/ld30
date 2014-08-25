package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.math.Vector2;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		/*this.getSelectedUnits().clear();
		for(Unit unit : getTerrain().getUnits()) {
			if(unit.getPlayer() == this) {
				if(!isUnitMoving(unit)) {
					this.getSelectedUnits().add(unit);
				}
			}
		}
		float x, y;
		x = (float) (Math.random() * getTerrain().getWidth());
		y = (float) (Math.random() * getTerrain().getHeight());
		this.moveSelectedUnits(new Vector2(x, y));*/
	}
	
	private boolean isUnitMoving(Unit unit) {
		return !unit.getPath().getPoints().isEmpty();
	}
	
}
