package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SoldierSpawn extends Building{

	public SoldierSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SOLDIER_TENT);
	}

	@Override
	protected void handleKey(int key) {
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
