package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Worker extends AnimatedUnit {

	public Worker(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.WORKER_SPRITESHEET);
	}

	/*@Override
	public void render(SpriteBatch batch) {
		Vector2 screenPos = Constants.worldToScreen(this.getPosition(), getTerrain().getTilesHigh());
		batch.draw(Images.WORKER_IMAGE, screenPos.x, screenPos.y);
	}*/

	@Override
	public float getSpeed() {
		return 40.0f;
	}

	@Override
	public float getRadius() {
		return 12.0f;
	}

	@Override
	public float getAttackRadius() {
		return 24.0f;
	}

	@Override
	public float getAttackStrength() {
		return 60.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 0.0f;
	}

	@Override
	public float getRangeArmour() {
		return 0.0f;
	}

	@Override
	public float getStartHP() {
		return 120.0f;
	}

	@Override
	public float getStartSHP() {
		return 150.0f;
	}
	
}
