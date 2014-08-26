package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class RangeSpawn extends SpawnBuilding {

	public RangeSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.RANGED_TENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleKey(int key) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected Unit getSpawnUnit() {
		return new Ranged(player, new Vector2(), terrain);
	}

	@Override
	public float timeUntilSpawn() {
		return 10.0f;
	}

	@Override
	public float timeToCompletion() {
		return 16.0f;
	}


}
