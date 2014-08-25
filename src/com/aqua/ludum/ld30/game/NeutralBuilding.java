package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class NeutralBuilding extends Building {

	public NeutralBuilding(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.NEUTRAL_BUILDING);
	}

	@Override
	protected void handleKey(int key) {
	}
	
	@Override
	public float getStartHP() {
		return Float.POSITIVE_INFINITY;
	}
	
	
	@Override
	public void spawn() {
		super.spawn();
	}

	@Override
	public float timeUntilSpawn() {
		return 10.0f;
	}


}
