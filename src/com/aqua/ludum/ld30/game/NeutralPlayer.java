package com.aqua.ludum.ld30.game;

public class NeutralPlayer extends Player {
	
	public NeutralPlayer(String name) {
		super(name);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		this.getSelectedUnits().clear();
		for(Unit unit : getTerrain().getUnits()) {
			if(unit.getPlayer() == this) {
				if(!isUnitMoving(unit)) {
					this.getSelectedUnits().add(unit);
				}
			}
		}
		float x, y, xdir = Math.random() < 0.5 ? -1 : 1, ydir = Math.random() < 0.5 ? -1 : 1;
		x = xdir * (float) (Math.random() * 32);
		y = xdir * (float) (Math.random() * 32);
		this.moveSelectedUnits(getSelectedUnits().get(0).getPosition().cpy().add(x, y));

	}
	
	private boolean isUnitMoving(Unit unit) {
		return !unit.getPath().getPoints().isEmpty();
	}
	
}
