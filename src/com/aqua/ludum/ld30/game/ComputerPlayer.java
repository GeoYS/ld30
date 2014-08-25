package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.math.Vector2;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, Terrain terrain) {
		super(name, terrain);
		ai = new AI(this, terrain);
	}
	
	@Override
	public void update(float delta) {
		ai.perform();
	}
	
	private AI ai;
	
	private class AI {
		
		public AI(Player computerPlayer, Terrain terrain) {
			this.player = computerPlayer;
			this.terrain = terrain;
		}
		
		public void perform() {
			player.getSelectedUnits().clear();
			for(Unit unit : terrain.getUnits()) {
				if(unit.getPlayer() == this.player) {
					if(!isUnitMoving(unit)) {
						player.getSelectedUnits().add(unit);
					}
				}
			}
			float x, y;
			x = (float) (Math.random() * terrain.getWidth());
			y = (float) (Math.random() * terrain.getHeight());
			player.moveSelectedUnits(new Vector2(x, y));
		}
		
		private boolean isUnitMoving(Unit unit) {
			return !unit.getPath().getPoints().isEmpty();
		}
		
		private Terrain terrain;
		private Player player;
	}
	
}
