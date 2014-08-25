package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TankSpawn extends SpawnBuilding {

	public TankSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.TANK_TENT);
	}

	@Override
	protected void handleKey(int key) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void spawn() {
		super.spawn();
		terrain.spawnUnit(new Tank(player, new Vector2(0.0f, this.getRadius() + 32.0f).add(position), terrain));
	}

	@Override
	public float timeUntilSpawn() {
		// TODO Auto-generated method stub
		return 0;
	}

}
