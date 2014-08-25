package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class FastSpawn extends Building{

	public FastSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.FAST_TENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleKey(int key) {
		// TODO Auto-generated method stub
		
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
