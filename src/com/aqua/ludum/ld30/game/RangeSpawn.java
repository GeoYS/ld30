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
	public void spawn() {
		super.spawn();
		terrain.spawnUnit(new Ranged(player, new Vector2(0.0f, this.getRadius() + 32.0f).add(position), terrain));
	}

	@Override
	public float timeUntilSpawn() {
		return 10.0f;
	}


}
