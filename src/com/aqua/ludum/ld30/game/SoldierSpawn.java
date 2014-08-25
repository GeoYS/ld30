package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SoldierSpawn extends SpawnBuilding{

	public SoldierSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SOLDIER_TENT);
	}

	@Override
	protected void handleKey(int key) {
	}

	
	@Override
	public void spawn() {
		super.spawn();
		terrain.spawnUnit(new Soldier(player, new Vector2(0.0f, this.getRadius() + 32.0f).add(position), terrain));
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
