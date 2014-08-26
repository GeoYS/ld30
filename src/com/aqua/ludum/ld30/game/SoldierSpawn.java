package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class SoldierSpawn extends SpawnBuilding{

	public SoldierSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SOLDIER_TENT);
	}

	@Override
	protected void handleKey(int key) {
	}

	@Override
	protected Unit getSpawnUnit() {
		return new Soldier(player, new Vector2(), terrain);
	}

	@Override
	public float timeUntilSpawn() {
		return 10.0f;
	}

	@Override
	public float timeToCompletion() {
		return 12.0f;
	}


}
