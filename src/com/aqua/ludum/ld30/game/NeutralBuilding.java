package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class NeutralBuilding extends SpawnBuilding {

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
	public void update(float delta) {
		this.spiritCount = 1;
		super.update(delta);
	}
	
	
	@Override
	public void spawn() {
		super.spawn();
		terrain.spawnUnit(new Worker(terrain.getNeutralPlayer(), new Vector2(0.0f, this.getRadius() + 32.0f).add(position), terrain));
	}

	@Override
	public float timeUntilSpawn() {
		return 60.0f;
	}


}
