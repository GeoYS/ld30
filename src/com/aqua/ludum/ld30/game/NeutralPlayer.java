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
				if(Math.random() < delta / 2 ) {
					this.getSelectedUnits().add(unit);
					float x, y;
					x = (float) (Math.random() * 64) - 32;
					y = (float) (Math.random() * 64) - 32;
					this.moveSelectedUnits(getSelectedUnits().get(0).getPosition().cpy().add(x, y));
					this.getSelectedUnits().remove(unit);
				}
			}
		}
	}
	
	private boolean isUnitMoving(Unit unit) {
		return !unit.getPath().getPoints().isEmpty();
	}
	
}
